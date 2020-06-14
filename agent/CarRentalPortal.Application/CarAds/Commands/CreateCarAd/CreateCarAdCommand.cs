using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.CarAds.Commands.CreateCarAd
{
    public class CreateCarAdCommand : IRequest<int>
    {
        public int AgentId { get; set; }
        public string AgentContactInfo { get; set; }
        public string Brand { get; set; }
        public string CarModel { get; set; }
        public byte ProductionYear { get; set; }
        public SegmentType SegmentType { get; set; }
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
