using CarRentalPortal.Application.Reviews.Commands.CreateReview;
using CarRentalPortal.Application.Reviews.Commands.UpdateReviewStatus;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class ReviewsController : AbstractApiController
    {
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
