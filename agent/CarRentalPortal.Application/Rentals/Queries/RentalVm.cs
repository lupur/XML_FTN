using System.Collections.Generic;

namespace CarRentalPortal.Application.Rentals.Queries
{
    public class RentalVm
    {
        public ICollection<RentalDto> Rentals { get; set; }
    }
}
