using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Commands
{
    public class AddShoppingCartItemCommand : IRequest<int>
    {
        public int CarId { get; set; }
        public int OwnerId { get; set; }
        public int ShoppingCartId { get; set; }
    }
}
