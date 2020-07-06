using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.CarCategories.Queries.GetCarCategories;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarCategories.Queries.GetCarCategory
{
    public class GetCarCategoryQueryHandler : IRequestHandler<GetCarCategoryQuery, CarCategoryDto>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetCarCategoryQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<CarCategoryDto> Handle(GetCarCategoryQuery request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarCategories.FindAsync(request.Id);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarCategory), request.Id);
            }

            return _mapper.Map<CarCategoryDto>(entity);
        }
    }
}
