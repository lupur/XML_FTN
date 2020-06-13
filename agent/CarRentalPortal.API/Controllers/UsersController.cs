using CarRentalPortal.Application.Users.Commands;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class UsersController : ApiController
    {
        [HttpPost("register")]
        public async Task<ActionResult<int>> Register(CreateUserCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
