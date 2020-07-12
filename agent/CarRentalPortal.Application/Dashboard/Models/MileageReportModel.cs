using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.Dashboard.Models
{
    public class MileageReportModel : IMapFrom<Car>
    {
        public int Id { get; set; }
        public string CarBrandName { get; set; }
        public string CarModelName { get; set; }
        public string OwnerFullName { get; set; }
        public long Mileage { get; set; }
    }
}
