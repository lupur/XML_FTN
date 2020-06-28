using MediatR;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalsForBundle
{
    public class GetRentalsForBundleQuery : IRequest<RentalVm>
    {
        public int Id { get; set; }
    }
}
