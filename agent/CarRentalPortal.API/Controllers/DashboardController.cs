using CarRentalPortal.Application.Dashboard.Queries;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    //[Authorize(Roles = Roles.Administrator + "," + Roles.Agent)]
    public class DashboardController : AbstractApiController
    {
        [HttpGet("mileage-report")]
        public async Task<IActionResult> GetCarUsageReport()
        {
            var report = await Mediator.Send(new GetCarUsageReportQuery());
            return File(report.Content, report.ContentType, report.FileName);
        }
    }
}
