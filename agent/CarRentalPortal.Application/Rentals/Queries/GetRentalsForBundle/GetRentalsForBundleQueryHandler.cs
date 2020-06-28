using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Queries.GetRentalsForBundle
{
    public class GetRentalsForBundleQueryHandler : IRequestHandler<GetRentalsForBundleQuery, RentalVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _identityContext;
        private readonly IApplicationDbContext _appContext;

        public GetRentalsForBundleQueryHandler(IMapper mapper, IIdentityDbContext identityContext, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _identityContext = identityContext;
            _appContext = appContext;
        }

        public async Task<RentalVm> Handle(GetRentalsForBundleQuery request, CancellationToken cancellationToken)
        {
            var rentals = await _appContext.Rentals
                .Where(r => r.RentalBundleId == request.Id)
                .ProjectTo<RentalDto>(_mapper.ConfigurationProvider)
                .ToListAsync(cancellationToken);

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
