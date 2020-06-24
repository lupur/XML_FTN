using CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest;
using CarRentalPortal.Application.Rentals.Queries.GetRentalRequests;
using Microsoft.AspNetCore.Mvc;
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

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateRentalRequestCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
