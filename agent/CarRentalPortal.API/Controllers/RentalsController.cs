using CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest;
using CarRentalPortal.Application.Rentals.Commands.UpdateRentalRequest;
using CarRentalPortal.Application.Rentals.Queries;
using CarRentalPortal.Application.Rentals.Queries.GetRentalRequest;
using CarRentalPortal.Application.Rentals.Queries.GetRentalRequests;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class RentalsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<RentalVm>> Get()
        {
            return await Mediator.Send(new GetRentalRequestsQuery());
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
