using MediatR;

namespace CarRentalPortal.Application.Rentals.Queries.GetBundledRentalRequests
{
    public class GetBundledRentalRequestsQuery : IRequest<RentalBundleVm>
    {
        public int? OwnerId { get; set; }
    }
}
