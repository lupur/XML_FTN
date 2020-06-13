using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands
{
    public class CreateUserCommandHandler : IRequestHandler<CreateUserCommand, int>
    {
        private readonly IApplicationDbContext _context;
        private readonly IDataProtectionService _dataProtection;

        public CreateUserCommandHandler(IApplicationDbContext context, IDataProtectionService dataProtection)
        {
            _context = context;
            _dataProtection = dataProtection;
        }

        public Task<int> Handle(CreateUserCommand request, CancellationToken cancellationToken)
        {
            var salt = _dataProtection.GenerateSalt(128);
            var hashedPassword = _dataProtection.HashPassword(request.Password, Convert.FromBase64String(salt));

            var entity = new User
            {
                FirstName = request.FirstName,
                LastName = request.LastName,
                Email = request.Email,
                Password = hashedPassword,
                Salt = salt
            };

            _context.Users.Add(entity);
            await _context.SaveChangesAsync();

            return entity.Id;
        }
    }
}
