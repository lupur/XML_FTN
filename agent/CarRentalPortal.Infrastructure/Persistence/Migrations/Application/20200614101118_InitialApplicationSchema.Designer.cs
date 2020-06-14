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
    [Migration("20200614101118_InitialApplicationSchema")]
    partial class InitialApplicationSchema
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasDefaultSchema("carrentalportal.application")
                .HasAnnotation("ProductVersion", "3.1.5")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("CarRentalPortal.Core.Entities.CarAd", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<float>("AverageRating")
                        .HasColumnType("float");

                    b.Property<string>("Brand")
                        .HasColumnType("text");

                    b.Property<string>("CarModel")
                        .HasColumnType("text");

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

                    b.Property<int>("OwnerId")
                        .HasColumnType("int");

                    b.Property<byte>("ProductionYear")
                        .HasColumnType("tinyint");

                    b.Property<int>("SegmentType")
                        .HasColumnType("int");

                    b.Property<int>("TransmissionType")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.ToTable("CarAds");
                });
#pragma warning restore 612, 618
        }
    }
}