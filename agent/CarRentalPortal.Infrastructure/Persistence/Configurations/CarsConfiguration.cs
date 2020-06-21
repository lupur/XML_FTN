using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class CarsConfiguration : IEntityTypeConfiguration<Car>
    {
        public void Configure(EntityTypeBuilder<Car> builder)
        {
            builder
                .HasKey(c => c.Id);

            builder
                .Property(c => c.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasOne(c => c.CarBrand)
                .WithMany(cb => cb.Cars)
                .HasForeignKey(c => c.CarBrandName);

            builder
                .HasOne(c => c.CarModel)
                .WithMany(cb => cb.Cars)
                .HasForeignKey(c => c.CarModelName);
        }
    }
}
