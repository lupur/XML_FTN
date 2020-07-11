using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Commands
{
    public class EmptyShoppingCartCommand : IRequest
    {
        public int Id { get; set; }
    }
}
