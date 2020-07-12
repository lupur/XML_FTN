using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Dashboard.Models;
using CarRentalPortal.Application.Dashboard.Queries;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Dashboard.QueryHandlers
{
    public class GetCommentReportQueryHandler : IRequestHandler<GetCommentReportQuery, CarReportModel>
    {
        private readonly IMapper _mapper;
        private readonly IReportingService _reporting;
        private readonly IApplicationDbContext _context;

        public GetCommentReportQueryHandler(IMapper mapper, IReportingService reporting, IApplicationDbContext context)
        {
            _mapper = mapper;
            _reporting = reporting;
            _context = context;
        }

        public async Task<CarReportModel> Handle(GetCommentReportQuery request, CancellationToken cancellationToken)
        {
            var comments = await _context.Reviews.ProjectTo<CommentReportModel>(_mapper.ConfigurationProvider).ToListAsync(cancellationToken);
            var commentReport = new CommentAggregateReportModel
            {
                CommentCount = comments.Count,
                Comments = comments
            };

            return new CarReportModel
            {
                FileName = "CarCommentReport.pdf",
                Content = await _reporting.GenerateCommentReport(commentReport)
            };
        }
    }
}
