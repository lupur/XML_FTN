using CarRentalPortal.Application.Rentals.Queries.GetRentalRequests;
using MediatR;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalRequest
{
    public class GetRentalRequestQuery : IRequest<RentalDto>
    {
        public int Id { get; set; }
    }
}
