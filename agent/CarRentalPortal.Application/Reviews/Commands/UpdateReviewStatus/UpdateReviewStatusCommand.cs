using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.Reviews.Commands.UpdateReviewStatus
{
    public class UpdateReviewStatusCommand : IRequest
    {
        public int Id { get; set; }
        public ReviewStatus Status { get; set; }
    }
}
