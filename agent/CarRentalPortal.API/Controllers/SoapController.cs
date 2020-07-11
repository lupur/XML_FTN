//using CarRentalAPI;
//using CarRentalPortal.Application._Common.Interfaces;
//using Microsoft.AspNetCore.Mvc;
//using System;
//using System.Collections.Generic;
//using System.Threading.Tasks;

//namespace CarRentalPortal.API.Controllers
//{
//    public class SoapController : AbstractApiController
//    {
//        private readonly BrandDetailsPort _client;

//        public SoapController(ICarRentalApiClientFactory factory)
//        {
//            _client = factory.CreateChannel();
//        }

//        [HttpGet("brands")]
//        public async Task<ActionResult<IEnumerable<object>>> GetCarBrands()
//        {
//            Task<AllBrandsResponse> task;
//            var request = new AllBrandsRequest1
//            {
//                AllBrandsRequest = new AllBrandsRequest
//                {
//                    test = "OK"
//                }
//            };

//            try
//            {
//                task = _client.AllBrandsAsync(request);

//                var result = await task;

//                return result.AllBrandsResponse1;
//            }
//            catch (Exception ex)
//            {
//                Console.WriteLine(ex.ToString());
//                return new StatusCodeResult(500);
//            }
//        }

//        [HttpGet("brands/{id}")]
//        public async Task<ActionResult<object>> GetCarBrand(long id)
//        {
//            Task<BrandByIdResponse1> task;
//            var request = new BrandByIdRequest1
//            {
//                BrandByIdRequest = new BrandByIdRequest { id = id }
//            };

//            try
//            {
//                task = _client.BrandByIdAsync(request);

//                var result = await task;

//                return result.BrandByIdResponse.Brand;
//            }
//            catch (Exception ex)
//            {
//                Console.WriteLine(ex.ToString());
//                return new StatusCodeResult(500);
//            }
//        }

//        [HttpPost("brands")]
//        public async Task<ActionResult> CreateCarBrand(CreateCarBrandSoapRequest soapRequest)
//        {
//            Task<AddBrandResponse1> task;
//            var request = new AddBrandRequest1
//            {
//                AddBrandRequest = new AddBrandRequest
//                {
//                    name = soapRequest.Name
//                }
//            };

//            try
//            {
//                task = _client.AddBrandAsync(request);

//                var result = await task;

//                return Ok();
//            }
//            catch (Exception ex)
//            {
//                Console.WriteLine(ex.ToString());
//                return new StatusCodeResult(500);
//            }
//        }

//        [HttpPost("users/register")]
//        public async Task<ActionResult<int>> Register(CreateUserSoapCommand command)
//        {
//            return await Mediator.Send(command);
//        }

//    }
//    public class CreateCarBrandSoapRequest
//    {
//        public string Name { get; set; }
//    }
//}
