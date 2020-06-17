using MediatR;

namespace CarRentalPortal.Application.Cars.Commands.DeleteCar
{
    public class DeleteCarCommand : IRequest
    {
        public int CarId { get; set; }
    }
}
