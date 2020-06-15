using System;

namespace CarRentalPortal.Core.Entities
{
    public class Rental
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int CarId { get; set; }
        public Car Car { get; set; }
        public int RentalBundleId { get; set; }
        public RentalBundle RentalBundle { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public DateTime RequestedOn { get; set; }
        public string Remarks { get; set; }
    }
}
