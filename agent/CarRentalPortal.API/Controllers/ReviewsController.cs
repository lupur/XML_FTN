using CarRentalPortal.Application.Reviews.Commands.CreateReview;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize]
    public class ReviewsController : ApiController
    {
        [HttpPost]
        public async Task<ActionResult<int>> Create(CreateReviewCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
