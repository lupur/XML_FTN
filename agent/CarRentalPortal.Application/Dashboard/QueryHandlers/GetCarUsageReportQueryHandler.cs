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
    public class GetCarUsageReportQueryHandler : IRequestHandler<GetCarUsageReportQuery, CarReportModel>
    {
        private readonly IMapper _mapper;
        private readonly IReportingService _reporting;
        private readonly IApplicationDbContext _context;

        public GetCarUsageReportQueryHandler(IMapper mapper, IReportingService reporting, IApplicationDbContext context)
        {
            _mapper = mapper;
            _reporting = reporting;
            _context = context;
        }

        public async Task<CarReportModel> Handle(GetCarUsageReportQuery request, CancellationToken cancellationToken)
        {
            var mileageReport = await _context.Cars
                .OrderByDescending(c => c.Mileage).Take(10).ProjectTo<MileageReportModel>(_mapper.ConfigurationProvider).ToListAsync();

            return new CarReportModel
            {
                FileName = "CarUsageReport.pdf",
                Content = await _reporting.GenerateMileageReport(mileageReport)
            };
        }
    }
}
