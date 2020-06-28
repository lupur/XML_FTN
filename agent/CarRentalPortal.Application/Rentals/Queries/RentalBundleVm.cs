using System.Collections.Generic;

namespace CarRentalPortal.Application.Rentals.Queries
{
    public class RentalBundleVm
    {
        public ICollection<RentalBundleDto> RentalBundles { get; set; }
    }
}
