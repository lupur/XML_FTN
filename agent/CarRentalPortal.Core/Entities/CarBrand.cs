using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class CarBrand
    {
        public string Name { get; set; }
        public ICollection<Car> Cars { get; set; }
        public ICollection<CarModel> CarModels { get; set; }
    }
}
