using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarAds.Commands.CreateCarAd;
using CarRentalPortal.Application.CarAds.Queries.GetCarAds;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator + "," + Roles.Agent)]
    public class CarAdsController : ApiController
    {
        [HttpGet]
        public async Task<CarAdVm> Get()
        {
            return await Mediator.Send(new GetCarAdsQuery());
        }

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarAdCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
