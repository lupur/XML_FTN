using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrands
{
    public class CarModelDto : IMapFrom<CarModel>
    {
        public string Name { get; set; }
        public string CarBrandName { get; set; }
    }
}