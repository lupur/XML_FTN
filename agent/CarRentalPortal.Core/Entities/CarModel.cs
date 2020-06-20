using System.Collections;
using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class CarModel
    {
        public string CarBrandName { get; set; }
        public CarBrand CarBrand { get; set; }
        public string Name { get; set; }
        public ICollection<Car> Cars { get; set; }
    }
}
