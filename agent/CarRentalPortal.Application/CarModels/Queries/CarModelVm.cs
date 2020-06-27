using System.Collections.Generic;

namespace CarRentalPortal.Application.CarModels.Queries
{
    public class CarModelVm
    {
        public ICollection<CarModelDto> CarModels { get; set; }
    }
}
