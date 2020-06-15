using Microsoft.EntityFrameworkCore.Migrations;
using MySql.Data.EntityFrameworkCore.Metadata;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class CreateCarCategoryAndUpdateCarSchema : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CarImages_CarAds_CarAdId",
                schema: "carrentalportal.application",
                table: "CarImages");

            migrationBuilder.DropTable(
                name: "CarAds",
                schema: "carrentalportal.application");

            migrationBuilder.CreateTable(
                name: "CarCategory",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    Name = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarCategory", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Cars",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    PricelistId = table.Column<int>(nullable: false),
                    CarCategoryId = table.Column<int>(nullable: false),
                    AgentId = table.Column<int>(nullable: false),
                    AgentContactInfo = table.Column<string>(nullable: true),
                    Brand = table.Column<string>(nullable: true),
                    CarModel = table.Column<string>(nullable: true),
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
                        name: "FK_Cars_CarCategory_CarCategoryId",
                        column: x => x.CarCategoryId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarCategory",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Cars_Pricelists_PricelistId",
                        column: x => x.PricelistId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "Pricelists",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Cars_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarCategoryId");

            migrationBuilder.CreateIndex(
                name: "IX_Cars_PricelistId",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "PricelistId");

            migrationBuilder.AddForeignKey(
                name: "FK_CarImages_Cars_CarAdId",
                schema: "carrentalportal.application",
                table: "CarImages",
                column: "CarAdId",
                principalSchema: "carrentalportal.application",
                principalTable: "Cars",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CarImages_Cars_CarAdId",
                schema: "carrentalportal.application",
                table: "CarImages");

            migrationBuilder.DropTable(
                name: "Cars",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "CarCategory",
                schema: "carrentalportal.application");

            migrationBuilder.CreateTable(
                name: "CarAds",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    AgentContactInfo = table.Column<string>(type: "text", nullable: true),
                    AgentId = table.Column<int>(type: "int", nullable: false),
                    AverageRating = table.Column<float>(type: "float", nullable: false),
                    Brand = table.Column<string>(type: "text", nullable: true),
                    CarModel = table.Column<string>(type: "text", nullable: true),
                    Color = table.Column<string>(type: "text", nullable: true),
                    FuelType = table.Column<int>(type: "int", nullable: false),
                    Location = table.Column<string>(type: "text", nullable: true),
                    Mileage = table.Column<long>(type: "bigint", nullable: false),
                    MileageConstraint = table.Column<long>(type: "bigint", nullable: true),
                    NumberOfSeats = table.Column<byte>(type: "tinyint", nullable: false),
                    PricelistId = table.Column<int>(type: "int", nullable: false),
                    ProductionYear = table.Column<short>(type: "smallint", nullable: false),
                    SegmentType = table.Column<int>(type: "int", nullable: false),
                    TransmissionType = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarAds", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CarAds_Pricelists_PricelistId",
                        column: x => x.PricelistId,
                        principalSchema: "carrentalportal.application",
                        principalTable: "Pricelists",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CarAds_PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds",
                column: "PricelistId");

            migrationBuilder.AddForeignKey(
                name: "FK_CarImages_CarAds_CarAdId",
                schema: "carrentalportal.application",
                table: "CarImages",
                column: "CarAdId",
                principalSchema: "carrentalportal.application",
                principalTable: "CarAds",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
