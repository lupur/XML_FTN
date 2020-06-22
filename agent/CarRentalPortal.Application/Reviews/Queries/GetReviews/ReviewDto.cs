using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System;

namespace CarRentalPortal.Application.Reviews.Queries.GetReviews
{
    public class ReviewDto : IMapFrom<Review>
    {
        public int Id { get; set; }
        public int AuthorId { get; set; }
        public int CarId { get; set; }
        public string AuthorDisplayName { get; set; }
        public string AuthorEmail { get; set; }
        public int Rating { get; set; }
        public string Comment { get; set; }
        public DateTime CreatedOn { get; set; }
    }
}
