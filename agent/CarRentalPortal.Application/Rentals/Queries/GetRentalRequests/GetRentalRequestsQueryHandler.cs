using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalRequests
{
    public class GetRentalRequestsQueryHandler : IRequestHandler<GetRentalRequestsQuery, RentalVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _identityContext;
        private readonly IApplicationDbContext _appContext;

        public GetRentalRequestsQueryHandler(IMapper mapper, IIdentityDbContext identityContext, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _identityContext = identityContext;
            _appContext = appContext;
        }

        public async Task<RentalVm> Handle(GetRentalRequestsQuery request, CancellationToken cancellationToken)
        {
            var rentals = await _appContext.Rentals
                    .ProjectTo<RentalDto>(_mapper.ConfigurationProvider)
                    .ToListAsync();

            foreach (var rental in rentals)
            {
                var user = await _identityContext.Users.FindAsync(rental.CustomerId);
                rental.CustomerFullName = $"{user.FirstName} {user.LastName}";
                rental.CustomerContactInfo = user.Email;
            }

            return new RentalVm
            {
                Rentals = rentals
            };
        }
    }
}
