using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.CommandHandlers
{
    public class RemoveShoppingCartItemCommandHandler : IRequestHandler<RemoveShoppingCartItemCommand>
    {
        private readonly IApplicationDbContext _context;

        public RemoveShoppingCartItemCommandHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Unit> Handle(RemoveShoppingCartItemCommand request, CancellationToken cancellationToken)
        {
            var item = await _context.ShoppingCartItems.FindAsync(request.Id);
            if (item == null)
                throw new NotFoundException(nameof(ShoppingCartItem), request.Id);

            _context.ShoppingCartItems.Remove(item);
            await _context.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
