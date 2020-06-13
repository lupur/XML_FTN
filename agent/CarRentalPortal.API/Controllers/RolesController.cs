using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Roles.Commands.CreateRole;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class RolesController : ApiController
    {
        [HttpPost, Authorize(Roles = Roles.Administrator)]
        public async Task<ActionResult<int>> Create(CreateRoleCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
