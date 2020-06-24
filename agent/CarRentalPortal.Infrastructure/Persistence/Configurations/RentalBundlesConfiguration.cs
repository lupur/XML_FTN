using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MySql.Data.EntityFrameworkCore.Extensions;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class RentalBundlesConfiguration : IEntityTypeConfiguration<RentalBundle>
    {
        public void Configure(EntityTypeBuilder<RentalBundle> builder)
        {
            builder
                .HasKey(rb => rb.Id);

            builder
                .Property(rb => rb.Id)
                .ValueGeneratedOnAdd();

            builder
                .Property(rb => rb.CreatedOn)
                .ValueGeneratedOnAdd();

            builder
                .Property(rb => rb.Status)
                .ForMySQLHasDefaultValue(RentalStatus.Pending)
                .ValueGeneratedOnAdd();

            builder
                .HasMany(rb => rb.Rentals)
                .WithOne(r => r.RentalBundle)
                .HasForeignKey(r => r.RentalBundleId);
        }
    }
}
