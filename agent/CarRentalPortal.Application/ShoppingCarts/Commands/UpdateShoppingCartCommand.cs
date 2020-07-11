using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Commands
{
    public class UpdateShoppingCartCommand : IRequest
    {
        public int Id { get; set; }
    }
}
