using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System.Collections.Generic;

namespace CarRentalPortal.Application.ShoppingCarts.Models
{
    public class ShoppingCartModel : IMapFrom<ShoppingCart>
    {
        public ShoppingCartModel()
        {
            Items = new List<ShoppingCartItemModel>();
        }

        public int Id { get; set; }
        public int UserId { get; set; }
        public int NumberOfItems { get; set; }
        public ICollection<ShoppingCartItemModel> Items { get; set; }
    }
}
