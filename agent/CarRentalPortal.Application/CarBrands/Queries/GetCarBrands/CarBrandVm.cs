using System.Collections.Generic;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrands
{
    public class CarBrandVm
    {
        public ICollection<CarBrandDto> CarBrands { get; set; }
    }
}
