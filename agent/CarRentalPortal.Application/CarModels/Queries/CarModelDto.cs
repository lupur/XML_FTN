using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.CarModels.Queries
{
    public class CarModelDto : IMapFrom<CarModel>
    {
        public string Name { get; set; }
        public string CarBrandName { get; set; }
    }
}