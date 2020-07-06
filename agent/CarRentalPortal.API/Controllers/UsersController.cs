using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.UserRoles.Queries.GetRoles;
using CarRentalPortal.Application.Users.Commands.CreateUser;
using CarRentalPortal.Application.Users.Commands.Login;
using CarRentalPortal.Application.Users.Commands.UpdateUserStatus;
using CarRentalPortal.Application.Users.Queries.GetUserById;
using CarRentalPortal.Application.Users.Queries.GetUsers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class UsersController : AbstractApiController
    {
        [HttpPost("login")]
        public async Task<ActionResult<UserDto>> Login(LoginCommand command)
        {
            var loginResponse = await Mediator.Send(command);
            var rolesResponse = await Mediator.Send(new GetUserRolesQuery { UserId = loginResponse.Id });

            return new UserDto
            {
                Id = loginResponse.Id,
                FirstName = loginResponse.FirstName,
                LastName = loginResponse.LastName,
                Username = loginResponse.Username,
                Email = loginResponse.Email,
                Token = loginResponse.Token,
                Roles = rolesResponse.Roles
            };
        }

        [HttpPost("register")]
        public async Task<ActionResult<int>> Register(CreateUserCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpGet, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<UserVm>> GetAll()
        {
            return await Mediator.Send(new GetUsersQuery());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<UserDto>> Get(int id)
        {
            return await Mediator.Send(new GetUserByIdQuery { Id = id });
        }

        [HttpPut("{id}/activate")]
        public async Task<ActionResult> Activate(int id, UpdateUserStatusCommand command)
        {
            if (id != command.Id)
                return BadRequest();
            await Mediator.Send(command);
            return NoContent();
        }

        [HttpPut("{id}/block")]
        public async Task<ActionResult> Block(int id, UpdateUserStatusCommand command)
        {
            if (id != command.Id)
                return BadRequest();
            await Mediator.Send(command);
            return NoContent();
        }
    }
}
