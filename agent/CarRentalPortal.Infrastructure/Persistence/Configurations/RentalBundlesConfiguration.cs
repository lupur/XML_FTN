﻿using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

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
        }
    }
}