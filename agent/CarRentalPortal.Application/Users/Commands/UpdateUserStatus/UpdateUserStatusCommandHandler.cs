using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.UpdateUserStatus
{
    public class UpdateUserStatusCommandHandler : IRequestHandler<UpdateUserStatusCommand>
    {
        private readonly IIdentityDbContext _context;

        public UpdateUserStatusCommandHandler(IIdentityDbContext context)
        {
            _context = context;
        }

        public async Task<Unit> Handle(UpdateUserStatusCommand request, CancellationToken cancellationToken)
        {
            var user = await _context.Users.FindAsync(request.Id);
            if (user == null)
            {
                throw new NotFoundException(nameof(User), request.Id);
            }

            user.Status = request.Status;

            await _context.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
