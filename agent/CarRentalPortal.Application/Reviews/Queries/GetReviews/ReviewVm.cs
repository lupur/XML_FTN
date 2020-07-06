using System.Collections.Generic;

namespace CarRentalPortal.Application.Reviews.Queries.GetReviews
{
    public class ReviewVm
    {
        public ICollection<ReviewDto> Reviews { get; set; }
    }
}
