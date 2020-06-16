using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.CarImages.Commands.UploadCarImage;
using CarRentalPortal.Application.Cars.Commands.CreateCar;
using CarRentalPortal.Application.Cars.Queries.GetCars;
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
    public class CarsController : ApiController
    {
        [HttpGet]
        public async Task<CarVm> Get()
        {
            return await Mediator.Send(new GetCarsQuery());
        }

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarCommand command)
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
                if (imagePath == null)
                {
                    return BadRequest();
                }

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
