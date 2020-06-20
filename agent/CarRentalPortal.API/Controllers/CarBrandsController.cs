using CarRentalPortal.Application.CarBrands.Queries.GetCarBrands;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class CarBrandsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<CarBrandVm>> Get()
        {
            return await Mediator.Send(new GetCarBrandsQuery());
        }
    }
}
