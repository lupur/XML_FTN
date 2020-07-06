using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalBundle
{
    public class GetRentalBundleQueryHandler : IRequestHandler<GetRentalBundleQuery, RentalBundleDto>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public GetRentalBundleQueryHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<RentalBundleDto> Handle(GetRentalBundleQuery request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.RentalBundles
                .FirstOrDefaultAsync(rb => rb.Id == request.Id);

            if (entity == null)
            {
                throw new NotFoundException(nameof(RentalBundle), request.Id);
            }

            return _mapper.Map<RentalBundleDto>(entity);
        }
    }
}
