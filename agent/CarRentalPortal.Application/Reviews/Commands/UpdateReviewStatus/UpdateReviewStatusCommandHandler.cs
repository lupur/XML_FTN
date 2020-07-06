using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Reviews.Commands.UpdateReviewStatus
{
    public class UpdateReviewStatusCommandHandler : IRequestHandler<UpdateReviewStatusCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public UpdateReviewStatusCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(UpdateReviewStatusCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.Reviews.FindAsync(request.Id);
            if (entity == null)
            {
                throw new NotFoundException(nameof(Review), request.Id);
            }

            entity.Status = request.Status;

            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
