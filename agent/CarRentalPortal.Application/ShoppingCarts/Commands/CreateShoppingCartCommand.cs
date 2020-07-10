using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Commands
{
    public class CreateShoppingCartCommand : IRequest<int>
    {
        public int UserId { get; set; }
    }
}
