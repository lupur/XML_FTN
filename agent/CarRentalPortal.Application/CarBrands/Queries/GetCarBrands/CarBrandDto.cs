using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System.Collections.Generic;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrands
{
    public class CarBrandDto : IMapFrom<CarBrand>
    {
        public string Name { get; set; }
        public ICollection<CarModelDto> CarModels { get; set; }
    }
}
