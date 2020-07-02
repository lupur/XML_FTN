using CarRentalPortal.Application.Rentals.Queries;
using CarRentalPortal.Application.Rentals.Queries.GetBundledRentalRequests;
using CarRentalPortal.Application.Rentals.Queries.GetRentalBundle;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Route("api/bundles")]
    public class RentalBundlesController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<RentalBundleVm>> GetAll()
        {
            return await Mediator.Send(new GetBundledRentalRequestsQuery());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<RentalBundleDto>> Get(int id)
        {
            return await Mediator.Send(new GetRentalBundleQuery { Id = id });
        }
    }
}
