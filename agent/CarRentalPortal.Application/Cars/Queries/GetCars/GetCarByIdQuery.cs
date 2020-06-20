using MediatR;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class GetCarByIdQuery : IRequest<CarDto>
    {
        public int Id { get; set; }
    }
}
