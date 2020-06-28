using AutoMapper;
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
            var rentalBundle = await _appContext.RentalBundles
                .Include(rb => rb.Rentals)
                .FirstOrDefaultAsync(rb => rb.Id == request.Id);
            if (rentalBundle == null)
            {
                throw new NotFoundException(nameof(RentalBundle), request.Id);
            }

            var rentals = _mapper.Map<IEnumerable<RentalDto>>(rentalBundle.Rentals)
                .ToList();
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
