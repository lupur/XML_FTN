using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.CreateRentalRequest
{
    public class CreateRentalRequestCommandHandler : IRequestHandler<CreateRentalRequestCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateRentalRequestCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<int> Handle(CreateRentalRequestCommand request, CancellationToken cancellationToken)
        {
            var entity = new RentalBundle { NumberOfItems = request.Rentals.Count };

            await _appContext.RentalBundles.AddAsync(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            foreach (var rentalRequest in request.Rentals)
            {
                _appContext.Rentals.Add(new Rental
                {
                    RentalBundleId = entity.Id,
                    UserId = rentalRequest.UserId,
                    CarId = rentalRequest.CarId,
                    StartDate = rentalRequest.StartDate,
                    EndDate = rentalRequest.EndDate,
                    Remarks = rentalRequest.Remarks
                });
            }
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
