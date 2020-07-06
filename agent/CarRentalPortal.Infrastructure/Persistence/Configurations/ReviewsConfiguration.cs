using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MySql.Data.EntityFrameworkCore.Extensions;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class ReviewsConfiguration : IEntityTypeConfiguration<Review>
    {
        public void Configure(EntityTypeBuilder<Review> builder)
        {
            builder
                .HasKey(r => r.Id);

            builder
                .Property(r => r.Id)
                .ValueGeneratedOnAdd();

            builder
                .Property(r => r.Status)
                .ForMySQLHasDefaultValue(ReviewStatus.Pending)
                .ValueGeneratedOnAdd();

            builder
                .Property(r => r.CreatedOn)
                .ForMySQLHasDefaultValueSql("CURRENT_TIMESTAMP")
                .ValueGeneratedOnAdd();

            builder
                .HasOne(r => r.Car)
                .WithMany(c => c.Reviews)
                .HasForeignKey(r => r.CarId);
        }
    }
}
