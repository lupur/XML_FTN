using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class RentalsConfiguration : IEntityTypeConfiguration<Rental>
    {
        public void Configure(EntityTypeBuilder<Rental> builder)
        {
            builder
                .HasKey(r => r.Id);

            builder
                .Property(r => r.Id)
                .ValueGeneratedOnAdd();

            builder
                .Property(r => r.RequestedOn)
                .ValueGeneratedOnAdd();
                
            builder
                .HasOne(r => r.RentalBundle)
                .WithMany(rb => rb.Rentals)
                .HasForeignKey(r => r.RentalBundleId);
        }
    }
}
