using AutoMapper;
using CarRentalPortal.Application._Common.Constants;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.Login
{
    public class LoginCommandHandler : IRequestHandler<LoginCommand, LoginResultVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _context;
        private readonly IDataProtectionService _dataProtection;

        public LoginCommandHandler(
            IMapper mapper,
            IIdentityDbContext context,
            IDataProtectionService dataProtection)
        {
            _mapper = mapper;
            _context = context;
            _dataProtection = dataProtection;
        }

        public async Task<LoginResultVm> Handle(LoginCommand request, CancellationToken cancellationToken)
        {
            var entity = await _context.Users
                .Include(u => u.Roles)
                .ThenInclude(ur => ur.Role)
                .FirstOrDefaultAsync(u => u.Username == request.UsernameOrEmail || u.Email == request.UsernameOrEmail);
            if (entity == null)
            {
                throw new NotFoundException(nameof(User), request.UsernameOrEmail);
            }

            if (!_dataProtection.ValidatePassword(request.Password, entity.Password, entity.Salt))
            {
                throw new LoginException();
            }

            //var userRoles = await _context.UserRoles
            //    .Where(ur => ur.UserId == entity.Id)
            //    .Select(r => r.Role)
            //    .ToListAsync(cancellationToken);

            return new LoginResultVm
            {
                Id = entity.Id,
                FirstName = entity.FirstName,
                LastName = entity.LastName,
                Username = entity.Username,
                Email = entity.Email,
                Token = GenerateAccessToken(entity)
            };
        }

        private string GenerateAccessToken(User user)
        {
            var tokenHandler = new JwtSecurityTokenHandler();
            var secretKey = Environment.GetEnvironmentVariable(AppConstants.SecretKeyString, EnvironmentVariableTarget.User);
            var signingCredentials = new SigningCredentials(new SymmetricSecurityKey(Encoding.ASCII.GetBytes(secretKey)), SecurityAlgorithms.HmacSha256Signature);

            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Issuer = AppConstants.Issuer,
                Audience = AppConstants.Audience,
                Subject = new ClaimsIdentity(GetValidClaims(user)),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = signingCredentials
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);

            return tokenHandler.WriteToken(token);
        }

        private List<Claim> GetValidClaims(User user)
        {
            var claims = new List<Claim>
            {
                new Claim(JwtRegisteredClaimNames.Sub, user.Username),
                new Claim(ClaimTypes.NameIdentifier, user.Id.ToString())
            };

            foreach (var role in user.Roles)
            {
                claims.Add(new Claim(ClaimTypes.Role, role.Role.Name));
            }

            return claims;
        }
    }
}
