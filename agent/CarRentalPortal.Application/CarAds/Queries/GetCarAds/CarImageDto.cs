using CarRentalPortal.Application.Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.CarAds.Queries.GetCarAds
{
    public class CarImageDto : IMapFrom<CarImage>
    {
        public string Uri { get; set; }
    }
}
