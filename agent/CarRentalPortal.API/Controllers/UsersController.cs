using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Application.UserRoles.Queries.GetUserRoles;
using CarRentalPortal.Application.Users.Commands.CreateUser;
using CarRentalPortal.Application.Users.Commands.InviteUser;
using CarRentalPortal.Application.Users.Commands.RemoveUser;
using CarRentalPortal.Application.Users.Commands.UpdateUserStatus;
using CarRentalPortal.Application.Users.Queries.GetUserById;
using CarRentalPortal.Application.Users.Queries.GetUsers;
using CarRentalPortal.Application.Users.Queries.GetUserShoppingCartId;
using CarRentalPortal.Application.Users.Queries.Login;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class UsersController : AbstractApiController
    {
        [HttpPost("login")]
        public async Task<ActionResult<UserDto>> Login(LoginQuery query)
        {
            var loginResponse = await Mediator.Send(query);
            var rolesResponse = await Mediator.Send(new GetUserRolesQuery { UserId = loginResponse.Id });
            var shoppingCartId = await Mediator.Send(new GetUserShoppingCartIdQuery { UserId = loginResponse.Id });

            return new UserDto
            {
                Id = loginResponse.Id,
                FirstName = loginResponse.FirstName,
                LastName = loginResponse.LastName,
                Username = loginResponse.Username,
                Email = loginResponse.Email,
                ShoppingCartId = shoppingCartId,
                Token = loginResponse.Token,
                Roles = rolesResponse.Roles
            };
        }

        [HttpPost("register")]
        public async Task<ActionResult<int>> Register(CreateUserCommand command)
        {
            var userId = await Mediator.Send(command);
            await Mediator.Send(new CreateShoppingCartCommand { UserId = userId });
            await Mediator.Send(new CreateUserSoapCommand
            {
                Username = command.Username,
                Email = command.Email,
                Password = command.Password,
                ConfirmPassword = command.Password
            });

            return userId;
        }

        [HttpPost("invite")]
        public async Task<ActionResult<int>> Invite(InviteUserCommand command)
        {
            var userId = await Mediator.Send(command);
            await Mediator.Send(new CreateShoppingCartCommand { UserId = userId });
            return userId;
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

        [HttpDelete("{id}")]
        public async Task<ActionResult> Remove(int id)
        {
            await Mediator.Send(new RemoveUserCommand { Id = id });
            return NoContent();
        }
    }
}
