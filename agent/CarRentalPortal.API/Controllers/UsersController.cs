using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Users.Commands.CreateUser;
using CarRentalPortal.Application.Users.Queries.GetUsers;
using Microsoft.AspNetCore.Authorization;
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

        [HttpGet, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<UserVm>> Get()
        {
            return await Mediator.Send(new GetUsersQuery());
        }
    }
}
