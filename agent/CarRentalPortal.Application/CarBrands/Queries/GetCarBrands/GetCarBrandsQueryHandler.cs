using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrands
{
    public class GetCarBrandsQueryHandler : IRequestHandler<GetCarBrandsQuery, CarBrandVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarBrandsQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarBrandVm> Handle(GetCarBrandsQuery request, CancellationToken cancellationToken)
        {
            return new CarBrandVm
            {
                CarBrands = await _appContext.CarBrands
                    .Include(cb => cb.CarModels)
                    .ProjectTo<CarBrandDto>(_mapper.ConfigurationProvider)
                    .ToListAsync()
            };
        }
    }
}
