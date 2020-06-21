using CarRentalPortal.Application.CarBrands.Queries.GetCarBrands;
using MediatR;
using System.Collections.Generic;

namespace CarRentalPortal.Application.CarBrands.Commands.CreateCarBrand
{
    public class CreateCarBrandCommand : IRequest<string>
    {
        public string Name { get; set; }
    }
}
