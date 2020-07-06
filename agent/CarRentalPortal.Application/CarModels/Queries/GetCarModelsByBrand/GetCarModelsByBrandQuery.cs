using MediatR;

namespace CarRentalPortal.Application.CarModels.Queries.GetCarModelsByBrand
{
    public class GetCarModelsByBrandQuery : IRequest<CarModelVm>
    {
        public string BrandName { get; set; }
    }
}
