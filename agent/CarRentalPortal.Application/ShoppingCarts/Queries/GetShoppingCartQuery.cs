using CarRentalPortal.Application.ShoppingCart.Models;
using CarRentalPortal.Application.ShoppingCarts.Models;
using MediatR;

namespace CarRentalPortal.Application.ShoppingCarts.Queries
{
    public class GetShoppingCartQuery : IRequest<ShoppingCartModel>
    {
        public int UserId { get; set; }
    }
}
