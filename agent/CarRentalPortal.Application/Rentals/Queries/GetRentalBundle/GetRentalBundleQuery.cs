using MediatR;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalBundle
{
    public class GetRentalBundleQuery : IRequest<RentalBundleDto>
    {
        public int Id { get; set; }
    }
}
