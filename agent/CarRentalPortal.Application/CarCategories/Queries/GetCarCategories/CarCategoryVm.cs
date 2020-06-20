using System.Collections.Generic;

namespace CarRentalPortal.Application.CarCategories.Queries.GetCarCategories
{
    public class CarCategoryVm
    {
        public ICollection<CarCategoryDto> CarCategories { get; set; }
    }
}
