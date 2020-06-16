using CarRentalPortal.Core.Enums;
using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class Car
    {
        public int Id { get; set; }
        public int CarCategoryId { get; set; }
        public CarCategory CarCategory { get; set; }
        public int AgentId { get; set; }
        public string AgentContactInfo { get; set; }
        public string Brand { get; set; }
        public string CarModel { get; set; }
        public short ProductionYear { get; set; }
        public FuelType FuelType { get; set; }
        public TransmissionType TransmissionType { get; set; }
        public string Color { get; set; }
        public string Location { get; set; }
        public long Mileage { get; set; }
        public long? MileageConstraint { get; set; }
        public byte NumberOfSeats { get; set; }
        public float AverageRating { get; set; }
        public ICollection<Review> Reviews { get; set; }
        public ICollection<CarImage> Images { get; set; }
    }
}
