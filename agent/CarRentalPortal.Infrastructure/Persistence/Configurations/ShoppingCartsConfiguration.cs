using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class ShoppingCartsConfiguration : IEntityTypeConfiguration<ShoppingCart>
    {
        public void Configure(EntityTypeBuilder<ShoppingCart> builder)
        {
            builder
                .HasKey(sc => sc.Id);

            builder
                .Property(sc => sc.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasIndex(sc => sc.UserId)
                .IsUnique(true);
        }
    }
}
