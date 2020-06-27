using MediatR;

namespace CarRentalPortal.Application.CarModels.Commands.CreateCarModel
{
    public class CreateCarModelCommand : IRequest<string>
    {
        public string Name { get; set; }
        public string BrandName { get; set; }
    }
}
