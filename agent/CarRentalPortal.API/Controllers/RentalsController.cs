using CarRentalPortal.Application.Rentals.CreateRentalRequest;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class RentalsController : AbstractApiController
    {
        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateRentalRequestCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
