using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application.Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarAds.Queries.GetCarAds
{
    public class GetCarAdsQueryHandler : IRequestHandler<GetCarAdsQuery, CarAdVm>
    {
        private readonly IApplicationDbContext _appContext;
        private readonly IMapper _mapper;

        public GetCarAdsQueryHandler(IApplicationDbContext appContext, IMapper mapper)
        {
            _appContext = appContext;
            _mapper = mapper;
        }

        public async Task<CarAdVm> Handle(GetCarAdsQuery request, CancellationToken cancellationToken)
        {
            return new CarAdVm
            {
                CarAds = await _appContext.CarAds
                    .ProjectTo<CarAdDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
