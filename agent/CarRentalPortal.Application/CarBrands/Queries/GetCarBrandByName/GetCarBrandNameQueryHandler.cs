using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.CarBrands.Queries.GetCarBrands;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarBrands.Queries.GetCarBrandByName
{
    public class GetCarBrandNameQueryHandler : IRequestHandler<GetCarBrandByNameQuery, CarBrandDto>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarBrandNameQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarBrandDto> Handle(GetCarBrandByNameQuery request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarBrands
                .Where(cb => cb.Name == request.CarBrandName)
                .Include(cb => cb.CarModels)
                .SingleOrDefaultAsync(cancellationToken);

            if (entity == null)
            {
                throw new NotFoundException(nameof(CarBrand), request.CarBrandName);
            }

            return _mapper.Map<CarBrandDto>(entity);
        }
    }
}
