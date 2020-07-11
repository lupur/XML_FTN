using CarRentalPortal.Core.Enums;

namespace CarRentalPortal.Application.ShoppingCarts.Models
{
    public class UpdateShoppingCartItemModel
    {
        public int Id { get; set; }
        public OrderStatus Status { get; set; }
    }
}
