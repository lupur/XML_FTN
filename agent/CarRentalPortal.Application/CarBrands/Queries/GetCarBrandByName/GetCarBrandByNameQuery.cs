using CarRentalPortal.Application.CarBrands.Queries.GetCarBrands;
using MediatR;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrandByName
{
    public class GetCarBrandByNameQuery : IRequest<CarBrandDto>
    {
        public string Name { get; set; }
    }
}
