using MediatR;

namespace CarRentalPortal.Application.CarBrands.Commands.CreateCarBrand
{
    public class CreateCarBrandCommand : IRequest<string>
    {
        public string Name { get; set; }
    }
}
