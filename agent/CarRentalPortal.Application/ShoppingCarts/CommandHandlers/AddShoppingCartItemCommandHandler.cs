using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.CommandHandlers
{
    public class AddShoppingCartItemCommandHandler : IRequestHandler<AddShoppingCartItemCommand, int>
    {
        private readonly IApplicationDbContext _context;

        public AddShoppingCartItemCommandHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<int> Handle(AddShoppingCartItemCommand request, CancellationToken cancellationToken)
        {
            var shoppingCart = await _context.ShoppingCarts.FindAsync(request.ShoppingCartId);
            if (shoppingCart == null)
                throw new NotFoundException(nameof(ShoppingCart), request.ShoppingCartId);

            var item = new ShoppingCartItem
            {
                CarId = request.CarId,
                ShoppingCartId = request.ShoppingCartId
            };

            await _context.ShoppingCartItems.AddAsync(item);
            shoppingCart.NumberOfItems++;
            await _context.SaveChangesAsync(cancellationToken);

            return item.Id;
        }
    }
}
