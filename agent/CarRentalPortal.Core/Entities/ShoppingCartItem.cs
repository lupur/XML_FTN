using CarRentalPortal.Core.Enums;

namespace CarRentalPortal.Core.Entities
{
    public class ShoppingCartItem
    {
        public int Id { get; set; }
        public int CarId { get; set; }
        public OrderStatus Status { get; set; }
        public int ShoppingCartId { get; set; }
        public ShoppingCart ShoppingCart { get; set; }
    }
}
