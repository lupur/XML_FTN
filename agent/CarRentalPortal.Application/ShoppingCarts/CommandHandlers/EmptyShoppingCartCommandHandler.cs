using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.CommandHandlers
{
    public class EmptyShoppingCartCommandHandler : IRequestHandler<EmptyShoppingCartCommand>
    {
        private readonly IApplicationDbContext _context;

        public EmptyShoppingCartCommandHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Unit> Handle(EmptyShoppingCartCommand request, CancellationToken cancellationToken)
        {
            var shoppingCart = await _context.ShoppingCarts.FindAsync(request.Id);
            if (shoppingCart == null)
                throw new NotFoundException(nameof(ShoppingCart), request.Id);

            _context.ShoppingCartItems.RemoveRange(shoppingCart.Items);
            await _context.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
