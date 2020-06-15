using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CarRentalPortal.Core.Entities
{
    public class CarImage
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Uri { get; set; }
        public int CarAdId { get; set; }
        public Car CarAd { get; set; }
    }
}
