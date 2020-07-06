using MediatR;

namespace CarRentalPortal.Application.CarModels.Queries.GetCarModel
{
    public class GetCarModelQuery : IRequest<CarModelDto>
    {
        public string Name { get; set; }
    }
}
