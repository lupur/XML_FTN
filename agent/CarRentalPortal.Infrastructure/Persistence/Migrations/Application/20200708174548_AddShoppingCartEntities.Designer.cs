﻿// <auto-generated />
using System;
using CarRentalPortal.Infrastructure.Persistence;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    [DbContext(typeof(ApplicationDbContext))]
    [Migration("20200708174548_AddShoppingCartEntities")]
    partial class AddShoppingCartEntities
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasDefaultSchema("carrentalportal.application")
                .HasAnnotation("ProductVersion", "3.1.5")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Car", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("CarBrandName")
                        .HasColumnType("varchar(32)");

                    b.Property<int>("CarCategoryId")
                        .HasColumnType("int");

                    b.Property<string>("CarModelName")
                        .HasColumnType("varchar(32)");

                    b.Property<string>("Color")
                        .HasColumnType("text");

                    b.Property<int>("FuelType")
                        .HasColumnType("int");

                    b.Property<string>("Location")
                        .HasColumnType("text");

                    b.Property<long>("Mileage")
                        .HasColumnType("bigint");

                    b.Property<long?>("MileageConstraint")
                        .HasColumnType("bigint");

                    b.Property<byte>("NumberOfSeats")
                        .HasColumnType("tinyint");

                    b.Property<string>("OwnerContactInfo")
                        .HasColumnType("text");

                    b.Property<string>("OwnerFullName")
                        .HasColumnType("text");

                    b.Property<int>("OwnerId")
                        .HasColumnType("int");

                    b.Property<short>("ProductionYear")
                        .HasColumnType("smallint");

                    b.Property<int>("TransmissionType")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("CarBrandName");

                    b.HasIndex("CarCategoryId");

                    b.HasIndex("CarModelName");

                    b.ToTable("Cars");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarBrand", b =>
                {
                    b.Property<string>("Name")
                        .HasColumnType("varchar(32)")
                        .HasMaxLength(32);

                    b.HasKey("Name");

                    b.ToTable("CarBrands");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarCategory", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("Name")
                        .HasColumnType("varchar(767)");

                    b.Property<double>("RentalValue")
                        .HasColumnType("double");

                    b.HasKey("Id");

                    b.HasIndex("Name")
                        .IsUnique();

                    b.ToTable("CarCategories");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarImage", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("CarAdId")
                        .HasColumnType("int");

                    b.Property<string>("Uri")
                        .HasColumnType("varchar(767)");

                    b.HasKey("Id");

                    b.HasIndex("CarAdId");

                    b.HasIndex("Uri")
                        .IsUnique();

                    b.ToTable("CarImages");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarModel", b =>
                {
                    b.Property<string>("Name")
                        .HasColumnType("varchar(32)")
                        .HasMaxLength(32);

                    b.Property<string>("CarBrandName")
                        .HasColumnType("varchar(32)");

                    b.HasKey("Name");

                    b.HasIndex("CarBrandName");

                    b.HasIndex("Name", "CarBrandName")
                        .IsUnique();

                    b.ToTable("CarModels");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Rental", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("CarId")
                        .HasColumnType("int");

                    b.Property<int>("CustomerId")
                        .HasColumnType("int");

                    b.Property<DateTime>("EndDate")
                        .HasColumnType("datetime");

                    b.Property<string>("Remarks")
                        .HasColumnType("text");

                    b.Property<int>("RentalBundleId")
                        .HasColumnType("int");

                    b.Property<DateTime>("RequestedOn")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("datetime")
                        .HasDefaultValueSql("CURRENT_TIMESTAMP");

                    b.Property<DateTime>("StartDate")
                        .HasColumnType("datetime");

                    b.HasKey("Id");

                    b.HasIndex("CarId");

                    b.HasIndex("RentalBundleId");

                    b.ToTable("Rentals");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.RentalBundle", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<DateTime>("CreatedOn")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("datetime");

                    b.Property<int>("NumberOfItems")
                        .HasColumnType("int");

                    b.Property<int>("Status")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasDefaultValue(1);

                    b.HasKey("Id");

                    b.ToTable("RentalBundles");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Review", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("AuthorDisplayName")
                        .HasColumnType("text");

                    b.Property<string>("AuthorEmail")
                        .HasColumnType("text");

                    b.Property<int>("AuthorId")
                        .HasColumnType("int");

                    b.Property<int>("CarId")
                        .HasColumnType("int");

                    b.Property<string>("Comment")
                        .HasColumnType("text");

                    b.Property<DateTime>("CreatedOn")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("datetime")
                        .HasDefaultValueSql("CURRENT_TIMESTAMP");

                    b.Property<int>("Rating")
                        .HasColumnType("int");

                    b.Property<int>("Status")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasDefaultValue(1);

                    b.HasKey("Id");

                    b.HasIndex("CarId");

                    b.ToTable("Reviews");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.ShoppingCart", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("NumberOfItems")
                        .HasColumnType("int");

                    b.Property<int>("UserId")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.ToTable("ShoppingCarts");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.ShoppingCartItem", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("CarId")
                        .HasColumnType("int");

                    b.Property<int>("ShoppingCartId")
                        .HasColumnType("int");

                    b.Property<int>("Status")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.HasIndex("ShoppingCartId");

                    b.ToTable("ShoppingCartItems");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Car", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.CarBrand", "CarBrand")
                        .WithMany("Cars")
                        .HasForeignKey("CarBrandName");

                    b.HasOne("CarRentalPortal.Core.Entities.CarCategory", "CarCategory")
                        .WithMany("Cars")
                        .HasForeignKey("CarCategoryId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("CarRentalPortal.Core.Entities.CarModel", "CarModel")
                        .WithMany("Cars")
                        .HasForeignKey("CarModelName");
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarImage", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.Car", "CarAd")
                        .WithMany("Images")
                        .HasForeignKey("CarAdId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarModel", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.CarBrand", "CarBrand")
                        .WithMany("CarModels")
                        .HasForeignKey("CarBrandName")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Rental", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.Car", "Car")
                        .WithMany("Rentals")
                        .HasForeignKey("CarId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("CarRentalPortal.Core.Entities.RentalBundle", "RentalBundle")
                        .WithMany("Rentals")
                        .HasForeignKey("RentalBundleId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.Review", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.Car", "Car")
                        .WithMany("Reviews")
                        .HasForeignKey("CarId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("CarRentalPortal.Core.Entities.ShoppingCartItem", b =>
                {
                    b.HasOne("CarRentalPortal.Core.Entities.ShoppingCart", "ShoppingCart")
                        .WithMany("Items")
                        .HasForeignKey("ShoppingCartId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}
