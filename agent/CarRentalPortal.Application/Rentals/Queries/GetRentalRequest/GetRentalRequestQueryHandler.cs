using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalRequest
{
    public class GetRentalRequestQueryHandler : IRequestHandler<GetRentalRequestQuery, RentalDto>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _identityContext;
        private readonly IApplicationDbContext _appContext;

        public GetRentalRequestQueryHandler(IMapper mapper, IIdentityDbContext identityContext, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _identityContext = identityContext;
            _appContext = appContext;
        }

        public async Task<RentalDto> Handle(GetRentalRequestQuery request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.Rentals.FindAsync(request.Id);
            if (entity == null)
            {
                throw new NotFoundException(nameof(Rental), request.Id);
            }

            return _mapper.Map<RentalDto>(entity);
        }
    }
}
