using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Application.ShoppingCarts.Models;
using CarRentalPortal.Application.ShoppingCarts.Queries;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    [Route("api/shopping-cart")]
    public class ShoppingCartController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<ShoppingCartModel>> GetUserShoppingCart([FromQuery] int userId)
        {
            return await Mediator.Send(new GetShoppingCartQuery { UserId = userId });
        }

        [HttpPost]
        public async Task<ActionResult<int>> CreateUserShoppingCart(CreateShoppingCartCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpPost("items/add")]
        public async Task<ActionResult<int>> AddItemToShoppingCart(AddShoppingCartItemCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
