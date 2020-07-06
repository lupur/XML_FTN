using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Queries.GetBundledRentalRequests
{
    public class GetBundledRentalRequestsQueryHandler : IRequestHandler<GetBundledRentalRequestsQuery, RentalBundleVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetBundledRentalRequestsQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<RentalBundleVm> Handle(GetBundledRentalRequestsQuery request, CancellationToken cancellationToken)
        {
            var rentalBundles = await _appContext.RentalBundles
                .ProjectTo<RentalBundleDto>(_mapper.ConfigurationProvider)
                .ToListAsync(cancellationToken);

            return new RentalBundleVm
            {
                RentalBundles = rentalBundles
            };
        }
    }
}
