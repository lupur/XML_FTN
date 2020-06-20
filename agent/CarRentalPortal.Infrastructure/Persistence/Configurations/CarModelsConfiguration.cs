using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class CarModelsConfiguration : IEntityTypeConfiguration<CarModel>
    {
        public void Configure(EntityTypeBuilder<CarModel> builder)
        {
            builder
                .HasKey(cm => new
                {
                    Name = cm.Name,
                    CarBrandName = cm.CarBrandName
                });

            builder
                .Property(cm => cm.Name)
                .HasMaxLength(32);

            builder
                .HasOne(cm => cm.CarBrand)
                .WithMany(cb => cb.CarModels)
                .HasForeignKey(cm => cm.CarBrandName);
        }
    }
}
