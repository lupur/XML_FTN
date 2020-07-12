using System.Collections.Generic;

namespace CarRentalPortal.Application.Dashboard.Models
{
    public class RatingAggregateReportModel
    {
        public int RatingCount { get; set; }
        public double HighestRating { get; set; }
        public double LowestRating { get; set; }
        public double AverageRating { get; set; }
        public ICollection<RatingReportModel> Ratings { get; set; }
    }
}
