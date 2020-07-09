using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Queries.GetUserShoppingCartId
{
    public class GetUserShoppingCartIdQueryHandler : IRequestHandler<GetUserShoppingCartIdQuery, int>
    {
        private readonly IApplicationDbContext _context;

        public GetUserShoppingCartIdQueryHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<int> Handle(GetUserShoppingCartIdQuery request, CancellationToken cancellationToken)
        {
            var shoppingCart = await _context.ShoppingCarts
                .FirstOrDefaultAsync(sc => sc.UserId == request.UserId, cancellationToken);
            if (shoppingCart == null)
                throw new NotFoundException(nameof(ShoppingCart), request.UserId);

            return shoppingCart.Id;
        }
    }
}
