using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarAds.Commands.CreateCarAd;
using CarRentalPortal.Application.CarAds.Queries.GetCarAds;
using CarRentalPortal.Application.CarImages.Commands.UploadCarImage;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.IO;
using System.Net.Http.Headers;
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

        [HttpPost("{carAdId}/images"), DisableRequestSizeLimit]
        public async Task<ActionResult<string>> Upload([FromRoute] int carAdId)
        {
            try
            {
                var resourcePath = Path.Combine("Resources", "Images", "Cars", $"{carAdId}");

                var imagePath = CopyImage(Request.Form.Files[0], resourcePath);

                var command = new UploadCarImageCommand
                {
                    CarAdId = carAdId,
                    ImagePath = imagePath
                };
                return await Mediator.Send(command);
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Internal server error: {ex}");
            }
        }

        private string CopyImage(IFormFile file, string resourcePath)
        {
            string imagePath = null;
            var destinationPath = Path.Combine(Directory.GetCurrentDirectory(), resourcePath);

            if (file.Length > 0)
            {
                var fileName = ContentDispositionHeaderValue.Parse(file.ContentDisposition).FileName.Trim('"');
                var fullPath = Path.Combine(destinationPath, fileName);
                imagePath = Path.Combine(resourcePath, fileName);

                if (!Directory.Exists(resourcePath))
                {
                    Directory.CreateDirectory(resourcePath);
                }

                using (var stream = new FileStream(fullPath, FileMode.Create))
                {
                    file.CopyTo(stream);
                }
            }
            return imagePath;
        }
    }
}
