using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarModels.Commands.CreateCarModel;
using CarRentalPortal.Application.CarModels.Commands.DeleteCarModel;
using CarRentalPortal.Application.CarModels.Queries;
using CarRentalPortal.Application.CarModels.Queries.GetCarModel;
using CarRentalPortal.Application.CarModels.Queries.GetCarModels;
using CarRentalPortal.Application.CarModels.Queries.GetCarModelsByBrand;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class CarModelsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<CarModelVm>> Get()
        {
            return await Mediator.Send(new GetCarModelsQuery());
        }

        [HttpGet("{name}")]
        public async Task<ActionResult<CarModelDto>> Get(string name)
        {
            return await Mediator.Send(new GetCarModelQuery { Name = name });
        }

        [HttpGet("brand/{name}")]
        public async Task<ActionResult<CarModelVm>> GetByBrand(string name)
        {
            return await Mediator.Send(new GetCarModelsByBrandQuery { BrandName = name });
        }

        [HttpPost, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<object>> Create(CreateCarModelCommand command)
        {
            return new { _ = await Mediator.Send(command) };
        }

        [HttpDelete("{name}"), Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult> Delete(string name)
        {
            await Mediator.Send(new DeleteCarModelCommand { Name = name });
            return NoContent();
        }
    }
}
