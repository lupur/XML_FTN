using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory;
using CarRentalPortal.Application.CarCategories.Commands.DeleteCarCategory;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategories;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategory;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator)]
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

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarCategoryCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> Delete(int id)
        {
            await Mediator.Send(new DeleteCarCategoryCommand { Id = id });
            return NoContent();
        }
    }
}
