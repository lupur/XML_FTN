using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest;
using CarRentalPortal.Application.Rentals.Commands.UpdateRentalRequest;
using CarRentalPortal.Application.Rentals.Queries;
using CarRentalPortal.Application.Rentals.Queries.GetRentalRequest;
using CarRentalPortal.Application.Rentals.Queries.GetRentalsForBundle;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class RentalsController : AbstractApiController
    {
        [HttpGet("{id}")]
        public async Task<ActionResult<RentalDto>> Get(int id)
        {
            return await Mediator.Send(new GetRentalRequestQuery { Id = id });
        }

        [HttpGet("bundle/{id}")]
        public async Task<ActionResult<RentalVm>> GetRentalsForBundle(int id)
        {
            return await Mediator.Send(new GetRentalsForBundleQuery { Id = id });
        }

        [HttpPost]
        public async Task<IEnumerable<int>> Create(CreateRentalRequestCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpPut("{bundleId}"), Authorize(Roles = Roles.Administrator)]
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
