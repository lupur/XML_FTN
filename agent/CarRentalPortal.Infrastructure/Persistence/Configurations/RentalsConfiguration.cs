using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MySql.Data.EntityFrameworkCore.Extensions;

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
                .ForMySQLHasDefaultValueSql("CURRENT_TIMESTAMP")
                .ValueGeneratedOnAdd();
                
            builder
                .HasOne(r => r.RentalBundle)
                .WithMany(rb => rb.Rentals)
                .HasForeignKey(r => r.RentalBundleId);
        }
    }
}
