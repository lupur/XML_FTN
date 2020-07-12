using CarRentalPortal.API.Constants;
using CarRentalPortal.Application.Dashboard.Queries;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    [Authorize(Roles = Roles.Administrator + "," + Roles.Agent)]
    public class DashboardController : AbstractApiController
    {
        [HttpGet("mileage-report")]
        public async Task<IActionResult> GetMileageReport()
        {
            var report = await Mediator.Send(new GetCarUsageReportQuery());
            return File(report.Content, report.ContentType, report.FileName);
        }

        [HttpGet("comment-report")]
        public async Task<IActionResult> GetCommentReport()
        {
            var report = await Mediator.Send(new GetCommentReportQuery());
            return File(report.Content, report.ContentType, report.FileName);
        }

        [HttpGet("rating-report")]
        public async Task<IActionResult> GetRatingReport()
        {
            var report = await Mediator.Send(new GetRatingReportQuery());
            return File(report.Content, report.ContentType, report.FileName);
        }
    }
}
