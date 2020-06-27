using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrands
{
    public class CarBrandDto : IMapFrom<CarBrand>
    {
        public string Name { get; set; }
    }
}
