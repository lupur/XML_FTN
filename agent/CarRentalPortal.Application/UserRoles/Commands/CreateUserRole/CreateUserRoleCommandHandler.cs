using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.UserRoles.Commands.CreateUserRole
{
    public class CreateUserRoleCommandHandler : IRequestHandler<CreateUserRoleCommand, (int, int)>
    {
        private readonly IIdentityDbContext _context;

        public CreateUserRoleCommandHandler(IIdentityDbContext context)
        {
            _context = context;
        }

        public async Task<(int, int)> Handle(CreateUserRoleCommand request, CancellationToken cancellationToken)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Id == request.UserId);
            if (user == null)
            {
                throw new NotFoundException(nameof(User), user.Id);
            }

            var role = await _context.Roles.FirstOrDefaultAsync(r => r.Id == request.RoleId);
            if (role == null)
            {
                throw new NotFoundException(nameof(Role), role.Id);
            }

            var entity = new UserRole
            {
                UserId = request.UserId,
                RoleId = request.RoleId
            };

            _context.UserRoles.Add(entity);
            await _context.SaveChangesAsync(cancellationToken);

            return (entity.UserId, entity.RoleId);
        }
    }
}
