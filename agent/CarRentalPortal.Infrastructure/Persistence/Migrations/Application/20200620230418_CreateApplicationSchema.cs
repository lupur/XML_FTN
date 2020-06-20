using System;
using Microsoft.EntityFrameworkCore.Migrations;
using MySql.Data.EntityFrameworkCore.Metadata;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class CreateApplicationSchema : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.EnsureSchema(
                name: "carrentalportal.application");

            migrationBuilder.CreateTable(
                name: "CarBrands",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Name = table.Column<string>(maxLength: 32, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarBrands", x => x.Name);
                });

            migrationBuilder.CreateTable(
                name: "CarCategories",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(nullable: true),
                    RentalValue = table.Column<double>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarCategories", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "RentalBundles",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_RentalBundles", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "CarModels",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    CarBrandName = table.Column<string>(nullable: false),
                    Name = table.Column<string>(maxLength: 32, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarModels", x => new { x.Name, x.CarBrandName });
                    table.ForeignKey(
                        name: "FK_CarModels_CarBrands_CarBrandName",
                        column: x => x.CarBrandName,
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarBrands",
                        principalColumn: "Name",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Cars",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    CarBrandName = table.Column<string>(nullable: true),
                    CarModelName = table.Column<string>(nullable: true),
                    CarCategoryId = table.Column<int>(nullable: false),
                    AgentId = table.Column<int>(nullable: false),
                    AgentContactInfo = table.Column<string>(nullable: true),
                    ProductionYear = table.Column<short>(nullable: false),
                    FuelType = table.Column<int>(nullable: false),
                    TransmissionType = table.Column<int>(nullable: false),
                    Color = table.Column<string>(nullable: true),
                    Location = table.Column<string>(nullable: true),
                    Mileage = table.Column<long>(nullable: false),
                    MileageConstraint = table.Column<long>(nullable: true),
                    NumberOfSeats = table.Column<byte>(nullable: false),
                    AverageRating = table.Column<float>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Cars", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Cars_CarBrands_CarBrandName",
                        column: x => x.CarBrandName,
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarBrands",
                        principalColumn: "Name",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Cars_CarCategories_CarCategoryId",
                        column: x => x.CarCategoryId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarCategories",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Cars_CarModels_CarBrandName_CarModelName",
                        columns: x => new { x.CarBrandName, x.CarModelName },
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarModels",
                        principalColumns: new[] { "Name", "CarBrandName" },
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "CarImages",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    Uri = table.Column<string>(nullable: true),
                    CarAdId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarImages", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CarImages_Cars_CarAdId",
                        column: x => x.CarAdId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "Cars",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Rentals",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    UserId = table.Column<int>(nullable: false),
                    CarId = table.Column<int>(nullable: false),
                    RentalBundleId = table.Column<int>(nullable: false),
                    StartDate = table.Column<DateTime>(nullable: false),
                    EndDate = table.Column<DateTime>(nullable: false),
                    RequestedOn = table.Column<DateTime>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    Remarks = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Rentals", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Rentals_Cars_CarId",
                        column: x => x.CarId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "Cars",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Rentals_RentalBundles_RentalBundleId",
                        column: x => x.RentalBundleId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "RentalBundles",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Reviews",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    AuthorId = table.Column<int>(nullable: false),
                    CarId = table.Column<int>(nullable: false),
                    AuthorDisplayName = table.Column<string>(nullable: true),
                    AuthorEmail = table.Column<string>(nullable: true),
                    Rating = table.Column<int>(nullable: false),
                    Comment = table.Column<string>(nullable: true),
                    CreatedOn = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Reviews", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Reviews_Cars_CarId",
                        column: x => x.CarId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "Cars",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CarCategories_Name",
                schema: "carrentalportal.application",
                table: "CarCategories",
                column: "Name",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_CarImages_CarAdId",
                schema: "carrentalportal.application",
                table: "CarImages",
                column: "CarAdId");

            migrationBuilder.CreateIndex(
                name: "IX_CarImages_Uri",
                schema: "carrentalportal.application",
                table: "CarImages",
                column: "Uri",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_CarModels_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels",
                column: "CarBrandName");

            migrationBuilder.CreateIndex(
                name: "IX_Cars_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarCategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Cars_CarBrandName_CarModelName",
                schema: "carrentalportal.application",
                table: "Cars",
                columns: new[] { "CarBrandName", "CarModelName" });

            migrationBuilder.CreateIndex(
                name: "IX_Rentals_CarId",
                schema: "carrentalportal.application",
                table: "Rentals",
                column: "CarId");

            migrationBuilder.CreateIndex(
                name: "IX_Rentals_RentalBundleId",
                schema: "carrentalportal.application",
                table: "Rentals",
                column: "RentalBundleId");

            migrationBuilder.CreateIndex(
                name: "IX_Reviews_CarId",
                schema: "carrentalportal.application",
                table: "Reviews",
                column: "CarId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CarImages",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "Rentals",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "Reviews",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "RentalBundles",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "Cars",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "CarCategories",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "CarModels",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "CarBrands",
                schema: "carrentalportal.application");
        }
    }
}
