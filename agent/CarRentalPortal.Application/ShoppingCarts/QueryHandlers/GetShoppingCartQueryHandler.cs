using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Models;
using CarRentalPortal.Application.ShoppingCarts.Queries;
using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.QueryHandlers
{
    public class GetShoppingCartQueryHandler : IRequestHandler<GetShoppingCartQuery, ShoppingCartModel>
    {
        private readonly IIdentityDbContext _identity;
        private readonly IApplicationDbContext _context;
        private readonly IConfigurationProvider _provider;

        public GetShoppingCartQueryHandler(IIdentityDbContext identity, IApplicationDbContext context, IMapper mapper)
        {
            _identity = identity;
            _context = context;
            _provider = mapper.ConfigurationProvider;
        }

        public async Task<ShoppingCartModel> Handle(GetShoppingCartQuery request, CancellationToken cancellationToken)
        {
            var shoppingCart = await _context.ShoppingCarts
                .ProjectTo<ShoppingCartModel>(_provider).FirstOrDefaultAsync(sc => sc.UserId == request.UserId);
            if (shoppingCart == null)
                throw new NotFoundException(nameof(ShoppingCart), request.UserId);

            var user = await _identity.Users.FindAsync(request.UserId);
            if (user == null)
                throw new NotFoundException(nameof(User), request.UserId);

            shoppingCart.UserFirstName = user.FirstName;
            shoppingCart.Items = await _context.ShoppingCartItems
                .Where(sci => sci.ShoppingCartId == shoppingCart.Id)
                .Where(sci => sci.Status == OrderStatus.Pending)
                .ProjectTo<ShoppingCartItemModel>(_provider).ToListAsync(cancellationToken);
            shoppingCart.NumberOfItems = shoppingCart.Items.Count();

            return shoppingCart;
        }
    }
}
