using MediatR;

namespace CarRentalPortal.Application.CarModels.Commands.DeleteCarModel
{
    public class DeleteCarModelCommand : IRequest
    {
        public string Name { get; set; }
    }
}
