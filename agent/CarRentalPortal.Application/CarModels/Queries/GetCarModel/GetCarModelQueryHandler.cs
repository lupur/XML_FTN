using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarModels.Queries.GetCarModel
{
    public class GetCarModelQueryHandler : IRequestHandler<GetCarModelQuery, CarModelDto>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarModelQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarModelDto> Handle(GetCarModelQuery request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarModels.FindAsync(request.Name);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarModel), request.Name);
            }

            return _mapper.Map<CarModelDto>(entity);
        }
    }
}
