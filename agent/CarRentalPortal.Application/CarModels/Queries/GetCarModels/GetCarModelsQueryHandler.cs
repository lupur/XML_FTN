using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarModels.Queries.GetCarModels
{
    public class GetCarModelsQueryHandler : IRequestHandler<GetCarModelsQuery, CarModelVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarModelsQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarModelVm> Handle(GetCarModelsQuery request, CancellationToken cancellationToken)
        {
            return new CarModelVm
            {
                CarModels = await _appContext.CarModels
                    .ProjectTo<CarModelDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
