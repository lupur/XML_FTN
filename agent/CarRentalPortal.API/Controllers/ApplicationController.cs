using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Application.Queries;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class ApplicationController : ApiController
    {
        [HttpGet("healthcheck"), Authorize(Roles = Roles.Administrator)]
        public async Task<string> GetApplicationHealth()
        {
            return await Mediator.Send(new GetApplicationHealthQuery());
        }
    }
}
