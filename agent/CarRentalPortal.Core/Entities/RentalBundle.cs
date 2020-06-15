using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class RentalBundle
    {
        public int Id { get; set; }
        public ICollection<Rental> Rentals { get; set; }
    }
}
