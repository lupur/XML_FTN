using CarRentalPortal.API.Constants;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class UserRolesController : ApiController
    {
        [HttpPost, Authorize(Roles = Roles.Administrator)]
        public async Task<(int, int)> Create(CreateUserRoleCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
