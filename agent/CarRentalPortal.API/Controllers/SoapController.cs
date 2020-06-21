using CarRentalAPI;
using CarRentalPortal.Application._Common.Interfaces;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class SoapController : AbstractApiController
    {
        private readonly BrandDetailsPort _client;

        public SoapController(ICarRentalApiClientFactory factory)
        {
            _client = factory.CreateChannel();
        }

        [HttpGet("brands/{id}")]
        public async Task<ActionResult> GetCarBrand(int id)
        {
            Task<BrandDetailsResponse1> task;
            var request = new BrandDetailsRequest1
            {
                BrandDetailsRequest = new BrandDetailsRequest { id = 1 }
            };
            try
            {
                task = _client.BrandDetailsAsync(request);

                var result = await task;

                return Ok(result);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                return new StatusCodeResult(500);
            }
        }
    }
}
