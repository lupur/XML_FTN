using MediatR;

namespace CarRentalPortal.Application.CarBrands.Commands.DeleteCarBrand
{
    public class DeleteCarBrandCommand : IRequest
    {
        public string Name { get; set; }
    }
}
