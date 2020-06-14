using CarRentalPortal.Core.Enums;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CarRentalPortal.Core.Entities
{
    public class CarAd
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public int OwnerId { get; set; }
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
