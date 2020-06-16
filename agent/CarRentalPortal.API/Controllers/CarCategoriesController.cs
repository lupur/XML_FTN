using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator)]
    public class CarCategoriesController : ApiController
    {
        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarCategoryCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
