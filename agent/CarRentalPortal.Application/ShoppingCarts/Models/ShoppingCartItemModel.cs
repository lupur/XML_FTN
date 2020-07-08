using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;

namespace CarRentalPortal.Application.ShoppingCarts.Models
{
    public class ShoppingCartItemModel : IMapFrom<ShoppingCartItem>
    {
        public int Id { get; set; }
        public int CarId { get; set; }
        public OrderStatus Status { get; set; }
    }
}
