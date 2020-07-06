using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MySql.Data.EntityFrameworkCore.Extensions;

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
                .Property(u => u.Status)
                .ForMySQLHasDefaultValue(AccountStatus.Pending)
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
