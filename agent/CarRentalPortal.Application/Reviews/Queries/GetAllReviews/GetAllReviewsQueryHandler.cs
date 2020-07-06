using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Reviews.Queries.GetReviews;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Reviews.Queries.GetAllReviews
{
    public class GetAllReviewsQueryHandler : IRequestHandler<GetAllReviewsQuery, ReviewVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetAllReviewsQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<ReviewVm> Handle(GetAllReviewsQuery request, CancellationToken cancellationToken)
        {
            return new ReviewVm
            {
                Reviews = await _appContext.Reviews
                    .ProjectTo<ReviewDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
