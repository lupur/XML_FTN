using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Commands
{
    public class RemoveShoppingCartItemCommand : IRequest
    {
        public int Id { get; set; }
    }
}
