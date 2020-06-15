using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Pricelists.Commands.CreatePricelist;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator)]
    public class PricelistsController : ApiController
    {
        [HttpPost]
        public async Task<ActionResult<int>> Create(CreatePricelistCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
