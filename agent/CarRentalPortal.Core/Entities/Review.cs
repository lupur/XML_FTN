using CarRentalPortal.Core.Enums;
using System;

namespace CarRentalPortal.Core.Entities
{
    public class Review
    {
        public int Id { get; set; }
        public int AuthorId { get; set; }
        public int CarId { get; set; }
        public Car Car { get; set; }
        public string AuthorDisplayName { get; set; }
        public string AuthorEmail { get; set; }
        public int Rating { get; set; }
        public string Comment { get; set; }
        public ReviewStatus Status { get; set; }
        public DateTime CreatedOn { get; set; }
    }
}
