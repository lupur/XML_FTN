using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.Cars.Commands.CreateCar
{
    public class CreateCarCommand : IRequest<int>
    {
        public int AgentId { get; set; }
        public string AgentContactInfo { get; set; }
        public int CarCategoryId { get; set; }
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
    }
}
