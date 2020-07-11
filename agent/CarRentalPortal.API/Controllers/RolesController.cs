using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Roles.Commands.CreateRole;
using CarRentalPortal.Application.Roles.Queries;
using CarRentalPortal.Application.Roles.Queries.GetRoleById;
using CarRentalPortal.Application.Roles.Queries.GetRoles;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class RolesController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<RoleVm>> GetAll()
        {
            return await Mediator.Send(new GetRolesQuery());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<RoleDto>> GetById(int id)
        {
            return await Mediator.Send(new GetRoleByIdQuery { Id = id });
        }

        [HttpPost, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<int>> Create(CreateRoleCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
