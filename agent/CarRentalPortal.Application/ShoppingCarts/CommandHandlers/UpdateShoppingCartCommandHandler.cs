using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.CommandHandlers
{
    public class UpdateShoppingCartCommandHandler : IRequestHandler<UpdateShoppingCartCommand>
    {
        private readonly IApplicationDbContext _context;

        public UpdateShoppingCartCommandHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Unit> Handle(UpdateShoppingCartCommand request, CancellationToken cancellationToken)
        {
            var shoppingCart = await _context.ShoppingCarts.Include(sc => sc.Items).FirstOrDefaultAsync(sc => sc.Id == request.Id);
            if (shoppingCart == null)
                throw new NotFoundException(nameof(ShoppingCart), request.Id);

            UpdateShoppingCartItems(shoppingCart);
            await _context.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }

        private void UpdateShoppingCartItems(ShoppingCart shoppingCart)
        {
            foreach (var item in shoppingCart.Items)
            {
                item.Status = OrderStatus.Ordered;
            }
        }
    }
}
