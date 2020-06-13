using CarRentalPortal.Application.Common.Constants;
using CarRentalPortal.Application.Common.Exceptions;
using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Auth.Commands
{
    public class LoginCommandHandler : IRequestHandler<LoginCommand, string>
    {
        private readonly IApplicationDbContext _context;
        private readonly IDataProtectionService _dataProtection;

        public LoginCommandHandler(IApplicationDbContext context, IDataProtectionService dataProtection)
        {
            _context = context;
            _dataProtection = dataProtection;
        }

        public async Task<string> Handle(LoginCommand request, CancellationToken cancellationToken)
        {
            var entity = await _context.Users.FirstOrDefaultAsync(u => u.Email == request.Email);
            if (entity == null)
            {
                throw new NotFoundException(nameof(User), request.Email);
            }

            if (!_dataProtection.ValidatePassword(request.Password, entity.Password, entity.Salt))
            {
                throw new LoginException();
            }

            return GenerateAccessToken(entity);
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
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.Name, user.Id.ToString())
                }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = signingCredentials
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);

            return tokenHandler.WriteToken(token);
        }
    }
}
