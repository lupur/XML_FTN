using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MySql.Data.EntityFrameworkCore.Extensions;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class ShoppingCartItemsConfiguration : IEntityTypeConfiguration<ShoppingCartItem>
    {
        public void Configure(EntityTypeBuilder<ShoppingCartItem> builder)
        {
            builder
                .HasKey(sci => sci.Id);

            builder
                .Property(sci => sci.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasOne(sci => sci.ShoppingCart)
                .WithMany(sc => sc.Items)
                .HasForeignKey(sci => sci.ShoppingCartId);

            builder
                .Property(sci => sci.Status)
                .ForMySQLHasDefaultValue(OrderStatus.Pending)
                .ValueGeneratedOnAdd();
        }
    }
}
