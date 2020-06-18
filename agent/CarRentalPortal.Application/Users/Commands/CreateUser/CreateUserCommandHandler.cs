using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.CreateUser
{
    public class CreateUserCommandHandler : IRequestHandler<CreateUserCommand, int>
    {
        private readonly IIdentityDbContext _context;
        private readonly IDataProtectionService _dataProtection;

        public CreateUserCommandHandler(IIdentityDbContext context, IDataProtectionService dataProtection)
        {
            _context = context;
            _dataProtection = dataProtection;
        }

        public async Task<int> Handle(CreateUserCommand request, CancellationToken cancellationToken)
        {
            var salt = _dataProtection.GenerateSalt(128);
            var hashedPassword = _dataProtection.HashPassword(request.Password, Convert.FromBase64String(salt));

            var entity = new User
            {
                FirstName = request.FirstName,
                LastName = request.LastName,
                Username = request.Username,
                Password = hashedPassword,
                Email = request.Email,
                Salt = salt
            };

            _context.Users.Add(entity);
            await _context.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
