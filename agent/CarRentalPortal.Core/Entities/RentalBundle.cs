using System;
using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class RentalBundle
    {
        public RentalBundle()
        {
            Rentals = new List<Rental>();
        }

        public int Id { get; set; }
        public int NumberOfItems { get; set; }
        public DateTime CreatedOn { get; set; }
        public ICollection<Rental> Rentals { get; set; }
    }
}
