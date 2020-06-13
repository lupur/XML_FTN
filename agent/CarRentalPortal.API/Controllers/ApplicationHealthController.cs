using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Route("api/health")]
    [ApiController]
    public class ApplicationHealthController : ControllerBase
    {
        [HttpGet, Authorize]
        public Task<string> Ping()
        {
            return Task.FromResult("Beep-boop.");
        }
    }
}
