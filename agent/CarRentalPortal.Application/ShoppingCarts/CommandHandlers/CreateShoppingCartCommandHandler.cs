using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.ShoppingCarts.Commands;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.ShoppingCarts.CommandHandlers
{
    public class CreateShoppingCartCommandHandler : IRequestHandler<CreateShoppingCartCommand, int>
    {
        private readonly IApplicationDbContext _context;

        public CreateShoppingCartCommandHandler(IApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<int> Handle(CreateShoppingCartCommand request, CancellationToken cancellationToken)
        {
            var userShoppingCart = await _context.ShoppingCarts.FirstOrDefaultAsync(sc => sc.UserId == request.UserId);
            if (userShoppingCart != null)
            {
                return userShoppingCart.Id;
            }
            var shoppingCart = new ShoppingCart
            {
                UserId = request.UserId
            };
            await _context.ShoppingCarts.AddAsync(shoppingCart);
            await _context.SaveChangesAsync(cancellationToken);
            return shoppingCart.Id;
        }
    }
}
