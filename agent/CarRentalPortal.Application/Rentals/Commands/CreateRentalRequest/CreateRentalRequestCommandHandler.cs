using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest
{
    public class CreateRentalRequestCommandHandler : IRequestHandler<CreateRentalRequestCommand, IEnumerable<int>>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateRentalRequestCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<IEnumerable<int>> Handle(CreateRentalRequestCommand request, CancellationToken cancellationToken)
        {
            var rentalBundles = new List<RentalBundle>();

            rentalBundles.AddRange(HandleBundledRentals(request));
            rentalBundles.AddRange(HandleIndividualRentals(request));

            await _appContext.RentalBundles.AddRangeAsync(rentalBundles);
            await _appContext.SaveChangesAsync(cancellationToken);

            return rentalBundles.Select(rb => rb.Id);
        }

        private IEnumerable<RentalBundle> HandleBundledRentals(CreateRentalRequestCommand request)
        {
            var bundledRentalRequests = request.Rentals
                .Where(r => r.IsBundle)
                .GroupBy(r => r.OwnerId, r => r, (key, group) => new
                {
                    OwnerId = key,
                    Rentals = group.ToList()
                });

            foreach (var bundledRental in bundledRentalRequests)
            {
                var entity = new RentalBundle();
                foreach (var rental in bundledRental.Rentals)
                {
                    entity.NumberOfItems = bundledRental.Rentals.Count;
                    entity.Rentals.Add(new Rental
                    {
                        CustomerId = rental.CustomerId,
                        CarId = rental.CarId,
                        StartDate = rental.StartDate,
                        EndDate = rental.EndDate,
                        Remarks = rental.Remarks
                    });
                }
                yield return entity;
            }
        }

        private IEnumerable<RentalBundle> HandleIndividualRentals(CreateRentalRequestCommand request)
        {
            var individualRentalRequests = request.Rentals
                .Where(r => !r.IsBundle);

            foreach (var individualRental in individualRentalRequests)
            {
                var entity = new RentalBundle { NumberOfItems = 1 };
                entity.Rentals.Add(new Rental
                {
                    CustomerId = individualRental.CustomerId,
                    CarId = individualRental.CarId,
                    StartDate = individualRental.StartDate,
                    EndDate = individualRental.EndDate,
                    Remarks = individualRental.Remarks
                });
                yield return entity;
            }
        }
    }
}
