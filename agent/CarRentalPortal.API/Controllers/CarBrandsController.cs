using CarRentalPortal.Application.CarBrands.Commands.CreateCarBrand;
using CarRentalPortal.Application.CarBrands.Queries.GetCarBrandByName;
using CarRentalPortal.Application.CarBrands.Queries.GetCarBrands;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class CarBrandsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<CarBrandVm>> Get()
        {
            return await Mediator.Send(new GetCarBrandsQuery());
        }

        [HttpGet("{carBrandName}")]
        public async Task<ActionResult<CarBrandDto>> Get(string carBrandName)
        {
            return await Mediator.Send(new GetCarBrandByNameQuery { CarBrandName = carBrandName });
        }

        [HttpPost]
        public async Task<ActionResult<object>> Create(CreateCarBrandCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
