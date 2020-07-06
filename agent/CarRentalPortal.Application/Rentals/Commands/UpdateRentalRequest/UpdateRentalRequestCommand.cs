using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.Rentals.Commands.UpdateRentalRequest
{
    public class UpdateRentalRequestCommand : IRequest
    {
        public int BundleId { get; set; }
        public RentalStatus Status { get; set; }
    }
}
