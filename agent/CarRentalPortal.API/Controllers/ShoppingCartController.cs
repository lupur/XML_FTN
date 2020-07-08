using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Application.ShoppingCarts.Models;
using CarRentalPortal.Application.ShoppingCarts.Queries;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class ShoppingCartController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<ShoppingCartModel>> GetShoppingCart([FromQuery] int userId)
        {
            return await Mediator.Send(new GetShoppingCartQuery { UserId = userId });
        }

        [HttpPost]
        public async Task<ActionResult<int>> CreateIfNotExists([FromQuery] int userId, CreateShoppingCartCommand command)
        {
            if (userId != command.UserId)
                return BadRequest();
            return await Mediator.Send(command);
        }
    }
}
