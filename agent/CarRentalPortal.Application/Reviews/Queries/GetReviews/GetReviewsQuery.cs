using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.Reviews.Queries.GetReviews
{
    public class GetReviewsQuery : IRequest<ReviewVm>
    {
        public int CarId { get; set; }
        public ReviewStatus Status { get; set; }
    }
}
