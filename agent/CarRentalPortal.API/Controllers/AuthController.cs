using CarRentalPortal.Application.Auth.Commands;
using CarRentalPortal.Application.Auth.Commands.Login;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class AuthController : ApiController
    {
        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginCommand command)
        {
            var token = await Mediator.Send(command);
            return Ok(new { Token = token });
        }
    }
}
