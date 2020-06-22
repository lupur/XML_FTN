using AutoMapper;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class GetCarByIdQueryHandler : IRequestHandler<GetCarByIdQuery, CarDto>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarByIdQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarDto> Handle(GetCarByIdQuery request, CancellationToken cancellationToken)
        {
            return _mapper.Map<CarDto>(await _appContext.Cars
                .Include(c => c.CarCategory)
                .SingleOrDefaultAsync(c => c.Id == request.Id));
        }
    }
}
