using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Commands.UpdateRentalRequest
{
    public class UpdateRentalRequestCommandHandler : IRequestHandler<UpdateRentalRequestCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public UpdateRentalRequestCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(UpdateRentalRequestCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.RentalBundles
                .Include(rb => rb.Rentals)
                .SingleOrDefaultAsync(rb => rb.Id == request.BundleId);

            if (entity == null)
            {
                throw new NotFoundException(nameof(RentalBundle), request.BundleId);
            }

            entity.Status = request.Status;

            return Unit.Value;
        }
    }
}
