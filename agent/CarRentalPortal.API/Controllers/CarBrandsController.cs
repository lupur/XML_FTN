using CarRentalPortal.Application.CarBrands.Commands.CreateCarBrand;
using CarRentalPortal.Application.CarBrands.Commands.DeleteCarBrand;
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

        [HttpGet("{name}")]
        public async Task<ActionResult<CarBrandDto>> Get(string name)
        {
            return await Mediator.Send(new GetCarBrandByNameQuery { Name = name });
        }

        [HttpPost]
        public async Task<ActionResult<object>> Create(CreateCarBrandCommand command)
        {
            return new { _ = await Mediator.Send(command) };
        }

        [HttpDelete("{name}")]
        public async Task<ActionResult> Delete(string name)
        {
            await Mediator.Send(new DeleteCarBrandCommand { Name = name });

            return NoContent();
        }
    }
}
