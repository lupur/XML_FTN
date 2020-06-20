using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class CarBrandsConfiguration : IEntityTypeConfiguration<CarBrand>
    {
        public void Configure(EntityTypeBuilder<CarBrand> builder)
        {
            builder.HasKey(cb => cb.Name);

            builder
                .Property(cm => cm.Name)
                .HasMaxLength(32);
        }
    }
}
