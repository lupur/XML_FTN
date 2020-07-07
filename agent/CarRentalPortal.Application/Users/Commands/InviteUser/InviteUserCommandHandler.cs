using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.InviteUser
{
    public class InviteUserCommandHandler : IRequestHandler<InviteUserCommand, int>
    {
        private readonly IIdentityDbContext _context;
        private readonly IDataProtectionService _dataProtection;

        public InviteUserCommandHandler(IIdentityDbContext context, IDataProtectionService dataProtection)
        {
            _context = context;
            _dataProtection = dataProtection;
        }

        public async Task<int> Handle(InviteUserCommand request, CancellationToken cancellationToken)
        {
            var salt = _dataProtection.GenerateSalt(128);
            var hashedPassword = _dataProtection.HashPassword(request.Password, Convert.FromBase64String(salt));

            var user = new User
            {
                FirstName = request.FirstName,
                LastName = request.LastName,
                Username = request.Username,
                Email = request.Email,
                Password = hashedPassword,
                Status = request.Status,
                Salt = salt
            };

            await _context.Users.AddAsync(user);
            await _context.SaveChangesAsync(cancellationToken);

            foreach (var role in request.Roles)
            {
                await _context.UserRoles.AddAsync(new UserRole
                {
                    UserId = user.Id,
                    RoleId = role.Id
                });
            }
            await _context.SaveChangesAsync(cancellationToken);

            return user.Id;
        }
    }
}
