using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Dashboard.Models;
using CarRentalPortal.Application.Dashboard.Queries;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Dashboard.QueryHandlers
{
    public class GetRatingReportQueryHandler : IRequestHandler<GetRatingReportQuery, CarReportModel>
    {
        private readonly IMapper _mapper;
        private readonly IReportingService _reporting;
        private readonly IApplicationDbContext _context;

        public GetRatingReportQueryHandler(IMapper mapper, IReportingService reporting, IApplicationDbContext context)
        {
            _mapper = mapper;
            _reporting = reporting;
            _context = context;
        }

        public async Task<CarReportModel> Handle(GetRatingReportQuery request, CancellationToken cancellationToken)
        {
            var ratings = await _context.Reviews
                .ProjectTo<RatingReportModel>(_mapper.ConfigurationProvider).OrderByDescending(rrm => rrm.Rating).ToListAsync(cancellationToken);

            var ratingReport = new RatingAggregateReportModel
            {
                HighestRating = ratings.Max(r => r.Rating),
                LowestRating = ratings.Min(r => r.Rating),
                AverageRating = ratings.Average(r => r.Rating),
                RatingCount = ratings.Count,
                Ratings = ratings
            };

            return new CarReportModel
            {
                FileName = "CarRatingReport.pdf",
                Content = await _reporting.GenerateRatingReport(ratingReport)
            };
        }
    }
}
