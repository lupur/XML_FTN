using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarModels.Queries.GetCarModelsByBrand
{
    public class GetCarModelsByBrandQueryHandler : IRequestHandler<GetCarModelsByBrandQuery, CarModelVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarModelsByBrandQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarModelVm> Handle(GetCarModelsByBrandQuery request, CancellationToken cancellationToken)
        {
            return new CarModelVm
            {
                CarModels = await _appContext.CarModels
                    .Where(cm => cm.CarBrandName == request.BrandName)
                    .ProjectTo<CarModelDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
