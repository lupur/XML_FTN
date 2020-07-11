using CarRentalPortal.Core.Enums;
using MediatR;
using Newtonsoft.Json;

namespace CarRentalPortal.Application.Cars.Commands.CreateCar
{
    public class CreateCarCommand : IRequest<int>
    {
        [JsonProperty("carCategory")]
        public int CarCategoryId { get; set; }
        public string CarBrand { get; set; }
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
        public int OwnerId { get; set; }
        public string OwnerFullName { get; set; }
        public string OwnerContactInfo { get; set; }
    }
}
