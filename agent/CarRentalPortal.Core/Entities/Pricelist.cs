using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CarRentalPortal.Core.Entities
{
    public class Pricelist
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public double BasePrice { get; set; }
        public double MileagePenaltyPrice { get; set; }
        public ICollection<Car> CarAds { get; set; }
    }
}
