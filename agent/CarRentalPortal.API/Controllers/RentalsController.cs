using CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest;
using CarRentalPortal.Application.Rentals.Commands.UpdateRentalRequest;
using CarRentalPortal.Application.Rentals.Queries;
using CarRentalPortal.Application.Rentals.Queries.GetBundledRentalRequests;
using CarRentalPortal.Application.Rentals.Queries.GetRentalRequest;
using CarRentalPortal.Application.Rentals.Queries.GetRentalsForBundle;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class RentalsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<RentalBundleVm>> GetBundles()
        {
            return await Mediator.Send(new GetBundledRentalRequestsQuery());
        }

        [HttpGet("bundle/{id}")]
        public async Task<ActionResult<RentalVm>> GetRentalsForBundle(int id)
        {
            return await Mediator.Send(new GetRentalsForBundleQuery { Id = id });
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<RentalDto>> Get(int id)
        {
            return await Mediator.Send(new GetRentalRequestQuery { Id = id });
        }

        [HttpPost]
        public async Task<IEnumerable<int>> Create(CreateRentalRequestCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpPut("{bundleId}")]
        public async Task<ActionResult> Update(int bundleId, UpdateRentalRequestCommand command)
        {
            if (bundleId != command.BundleId)
            {
                return BadRequest();
            }

            await Mediator.Send(command);
            return NoContent();
        }
    }
}
