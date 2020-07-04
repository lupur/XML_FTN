using CarRentalPortal.Application.Reviews.Commands.CreateReview;
using CarRentalPortal.Application.Reviews.Commands.UpdateReviewStatus;
using CarRentalPortal.Application.Reviews.Queries.GetAllReviews;
using CarRentalPortal.Application.Reviews.Queries.GetReviews;
using CarRentalPortal.Core.Enums;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class ReviewsController : AbstractApiController
    {
        [HttpGet]
        public async Task<ActionResult<ReviewVm>> GetAll()
        {
            return await Mediator.Send(new GetAllReviewsQuery());
        }

        [HttpGet("car/{id}")]
        public async Task<ActionResult<ReviewVm>> GetReviewsForCar(int id, [FromQuery] ReviewStatus status)
        {
            return await Mediator.Send(new GetReviewsQuery { CarId = id, Status = status });
        }

        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateReviewCommand command)
        {
            return await Mediator.Send(command);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> Update(int id, UpdateReviewStatusCommand command)
        {
            if (id != command.Id)
            {
                return BadRequest();
            }

            await Mediator.Send(command);
            return NoContent();
        }
    }
}
