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

        [HttpDelete("{id}")]
        public async Task<ActionResult> EmptyShoppingCart(int id)
        {
            await Mediator.Send(new EmptyShoppingCartCommand { Id = id });
            return NoContent();
        }

        [HttpPost("items")]
        public async Task<ActionResult<int>> AddItemToShoppingCart(AddShoppingCartItemCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpDelete("items/{id}")]
        public async Task<ActionResult> RemoveItemFromShoppingCart(int id)
        {
            await Mediator.Send(new RemoveShoppingCartItemCommand { Id = id });
            return NoContent();
        }
    }
}
