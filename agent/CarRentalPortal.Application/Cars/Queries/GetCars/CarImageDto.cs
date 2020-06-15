using CarRentalPortal.Application.Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class CarImageDto : IMapFrom<CarImage>
    {
        public string Uri { get; set; }
    }
}
