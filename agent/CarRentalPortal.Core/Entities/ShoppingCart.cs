using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class ShoppingCart
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public int NumberOfItems { get; set; }
        public ICollection<ShoppingCartItem> Items { get; set; }
    }
}
