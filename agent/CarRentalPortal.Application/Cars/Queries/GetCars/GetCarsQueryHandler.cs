using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application.Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class GetCarsQueryHandler : IRequestHandler<GetCarsQuery, CarVm>
    {
        private readonly IApplicationDbContext _appContext;
        private readonly IMapper _mapper;

        public GetCarsQueryHandler(IApplicationDbContext appContext, IMapper mapper)
        {
            _appContext = appContext;
            _mapper = mapper;
        }

        public async Task<CarVm> Handle(GetCarsQuery request, CancellationToken cancellationToken)
        {
            return new CarVm
            {
                CarAds = await _appContext.Cars
                    .Include(ca => ca.Images)
                    .ProjectTo<CarDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
