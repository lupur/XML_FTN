using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarCategories.Queries.GetCarCategories
{
    public class GetCarCategoriesQueryHandler : IRequestHandler<GetCarCategoriesQuery, CarCategoryVm>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarCategoriesQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarCategoryVm> Handle(GetCarCategoriesQuery request, CancellationToken cancellationToken)
        {
            return new CarCategoryVm
            {
                CarCategories = await _appContext.CarCategories
                    .ProjectTo<CarCategoryDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
