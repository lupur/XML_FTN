using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CarRentalPortal.Infrastructure.Persistence.Configurations
{
    public class UsersConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder
                .HasKey(u => u.Id);

            builder
                .Property(u => u.Id)
                .ValueGeneratedOnAdd();

            builder
                .HasIndex(u => u.Username)
                .IsUnique(true);

            builder
                .HasIndex(u => u.Email)
                .IsUnique(true);
        }
    }
}
