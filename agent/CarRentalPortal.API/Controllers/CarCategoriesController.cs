using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory;
using CarRentalPortal.Application.CarCategories.Commands.DeleteCarCategory;
using CarRentalPortal.Application.CarCategories.Commands.UpdateCarCategory;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategories;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategory;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class CarCategoriesController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<CarCategoryVm>> Get()
        {
            return await Mediator.Send(new GetCarCategoriesQuery());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CarCategoryDto>> Get(int id)
        {
            return await Mediator.Send(new GetCarCategoryQuery { Id = id });
        }

        [HttpPost, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<int>> Create(CreateCarCategoryCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpDelete("{id}"), Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult> Delete(int id)
        {
            await Mediator.Send(new DeleteCarCategoryCommand { Id = id });
            return NoContent();
        }

        [HttpPut("{id}"), Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult> Update(int id, UpdateCarCategoryCommand command)
        {
            if (id != command.Id)
            {
                return BadRequest();
            }

            await Mediator.Send(command);
            return NoContent();
        }
    }
}
