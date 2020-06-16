using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class CarCategoriesConfiguration : IEntityTypeConfiguration<CarCategory>
    {
        public void Configure(EntityTypeBuilder<CarCategory> builder)
        {
            builder
                .HasKey(cc => cc.Id);

            builder
                .Property(cc => cc.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasMany(cc => cc.Cars)
                .WithOne(c => c.CarCategory)
                .HasForeignKey(cc => cc.CarCategoryId);

            builder
                .HasIndex(cc => cc.Name)
                .IsUnique(true);
        }
    }
}
