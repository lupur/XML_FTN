using AutoMapper;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Enums;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
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
            var car = _mapper.Map<CarDto>(
                await _appContext.Cars
                    .Include(c => c.CarCategory)
                    .FirstOrDefaultAsync(c => c.Id == request.Id));

            var acceptedCarReviews = await _appContext.Reviews
                .Where(r => r.CarId == request.Id)
                .Where(r => r.Status == ReviewStatus.Accepted)
                .ToListAsync(cancellationToken);

            var averageRating = 0d;
            if (acceptedCarReviews.Count > 0)
                averageRating = acceptedCarReviews.Average(r => r.Rating);

            car.AverageRating = averageRating;

            return car;
        }
    }
}
