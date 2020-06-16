using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class CarCategory
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public double RentalValue { get; set; }
        public ICollection<Car> Cars { get; set; }
    }
}
