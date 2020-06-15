using CarRentalPortal.Application.Common.Mappings;
using CarRentalPortal.Core.Entities;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class CarDto : IMapFrom<Car>
    {
        public int AgentId { get; set; }
        public string AgentContactInfo { get; set; }
        public string Brand { get; set; }
        public string CarModel { get; set; }
        public short ProductionYear { get; set; }
        public string Segment { get; set; }
        public string FuelType { get; set; }
        public string TransmissionType { get; set; }
        public string Color { get; set; }
        public string Location { get; set; }
        public long Mileage { get; set; }
        public long? MileageConstraint { get; set; }
        public byte NumberOfSeats { get; set; }
        public float AverageRating { get; set; }
        public ICollection<CarImageDto> Images { get; set; }
    }
}
