using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class CarImagesConfiguration : IEntityTypeConfiguration<CarImage>
    {
        public void Configure(EntityTypeBuilder<CarImage> builder)
        {
            builder
                .HasKey(ci => ci.Id);

            builder
                .Property(ci => ci.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasOne(ci => ci.CarAd)
                .WithMany(ca => ca.Images)
                .HasForeignKey(ci => ci.CarAdId);

            builder
                .HasIndex(ci => ci.Uri)
                .IsUnique(true);
        }
    }
}
