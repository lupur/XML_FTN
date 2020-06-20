using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategories;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator)]
    public class CarCategoriesController : AbstractApiController
    {
        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarCategoryCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpGet]
        public async Task<ActionResult<CarCategoryVm>> Get()
        {
            return await Mediator.Send(new GetCarCategoriesQuery());
        }
    }
}
