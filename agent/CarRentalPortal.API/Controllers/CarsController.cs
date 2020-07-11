using CarRentalPortal.API.Configurations;
using CarRentalPortal.Application.CarImages.Commands.DeleteCarImages;
using CarRentalPortal.Application.CarImages.Commands.UploadCarImage;
using CarRentalPortal.Application.Cars.Commands.CreateCar;
using CarRentalPortal.Application.Cars.Commands.DeleteCar;
using CarRentalPortal.Application.Cars.Queries.GetCars;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;
using System;
using System.IO;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class CarsController : AbstractApiController
    {
        private IOptions<ResourcesConfigSection> _configuration;

        public CarsController(IOptions<ResourcesConfigSection> configuration)
        {
            _configuration = configuration;
        }

        [HttpGet]
        public async Task<ActionResult<CarVm>> Get()
        {
            return await Mediator.Send(new GetCarsQuery());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CarDto>> Get(int id)
        {
            return await Mediator.Send(new GetCarByIdQuery { Id = id });
        }

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateCarCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpDelete("{carId}")]
        public async Task<ActionResult> Delete(int carId)
        {
            await Mediator.Send(new DeleteCarCommand { CarId = carId });

            var imagesPath = Path.Combine(_configuration.Value?.CarImagesPath, $"{carId}");
            await Mediator.Send(new DeleteCarImagesCommand { Path = imagesPath });

            return NoContent();
        }

        [HttpPost("{carId}/images"), DisableRequestSizeLimit]
        public async Task<ActionResult<string>> Upload([FromRoute] int carId)
        {
            try
            {
                var resourcePath = Path.Combine(_configuration.Value?.CarImagesPath, $"{carId}");
                var imagePath = CopyImage(Request.Form.Files[0], resourcePath);
                if (imagePath == null)
                {
                    return BadRequest();
                }

                var command = new UploadCarImageCommand
                {
                    CarId = carId,
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
