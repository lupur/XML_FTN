using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Dashboard.Models;
using PuppeteerSharp;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace CarRentalPortal.Infrastructure.Services
{
    public class ReportingService : IReportingService
    {
        public async Task<byte[]> GenerateMileageReport(ICollection<MileageReportModel> model)
        {
            await new BrowserFetcher().DownloadAsync(BrowserFetcher.DefaultRevision);

            var options = new LaunchOptions { Headless = true };
            using var browser = await Puppeteer.LaunchAsync(options);
            using var page = await browser.NewPageAsync();
            {
                var sb = new StringBuilder("<h1>Most used cars</h1>" +
                    "<h2 class='text-muted'>Criteria: Mileage</h2>" +
                    "<table>" +
                    "<thead><tr><th>Car ID</th><th>Brand</th><th>Model</th><th>Owner</th><th>Mileage (km)</th></tr></thead>");
                foreach (var car in model)
                {
                    sb.AppendLine(
                        $"<tbody><tr>" +
                            $"<td>{car.Id}</td>" +
                            $"<td>{car.CarBrandName}</td>" +
                            $"<td>{car.CarModelName}</td>" +
                            $"<td>{car.OwnerFullName}</td>" +
                            $"<td>{car.Mileage}</td>" +
                        $"</tr></tbody>");
                }
                sb.AppendLine("</table>");
                await page.SetContentAsync(sb.ToString());
                var result = await page.GetContentAsync();
                return await page.PdfDataAsync();
            }
        }

        public async Task<byte[]> GenerateRatingReport(RatingAggregateReportModel model)
        {
            await new BrowserFetcher().DownloadAsync(BrowserFetcher.DefaultRevision);

            var options = new LaunchOptions { Headless = true };
            using var browser = await Puppeteer.LaunchAsync(options);
            using var page = await browser.NewPageAsync();
            {
                var sb = new StringBuilder("<h1>Top Rated Cars</h1>" +
                    "<h2 class='text-muted'>Criteria: Rating</h2>" +
                    "<hr>" +
                    "<h3>Aggregate results</h3>" +
                    $"<ul><li>{model.HighestRating}</li><li>{model.LowestRating}</li><li>{model.AverageRating}</li></ul>" +
                    "<hr>" +
                    "<table>" +
                    "<thead><tr><th>Car ID</th><th>Brand</th><th>Model</th><th>Owner</th><th>Reviewer</th><th>Rating</th></tr></thead>");
                foreach (var rating in model.Ratings)
                {
                    sb.AppendLine(
                        $"<tbody><tr>" +
                            $"<td>{rating.CarId}</td>" +
                            $"<td>{rating.CarBrandName}</td>" +
                            $"<td>{rating.CarModelName}</td>" +
                            $"<td>{rating.OwnerFullName}</td>" +
                            $"<td>{rating.Reviewer}</td>" +
                            $"<td>{rating.Rating}</td>" +
                        $"</tr></tbody>");
                }
                sb.AppendLine("</table>");
                await page.SetContentAsync(sb.ToString());
                var result = await page.GetContentAsync();
                return await page.PdfDataAsync();
            }
        }
    }
}
