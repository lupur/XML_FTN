using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.CarCategories.Queries.GetCarCategories
{
    public class CarCategoryDto : IMapFrom<CarCategory>
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public double RentalValue { get; set; }
    }
}