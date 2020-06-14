using Microsoft.EntityFrameworkCore.Migrations;
using MySql.Data.EntityFrameworkCore.Metadata;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class InitialApplicationSchema : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.EnsureSchema(
                name: "carrentalportal.application");

            migrationBuilder.CreateTable(
                name: "CarAds",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    OwnerId = table.Column<int>(nullable: false),
                    Brand = table.Column<string>(nullable: true),
                    CarModel = table.Column<string>(nullable: true),
                    ProductionYear = table.Column<byte>(nullable: false),
                    SegmentType = table.Column<int>(nullable: false),
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
                    table.PrimaryKey("PK_CarAds", x => x.Id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CarAds",
                schema: "carrentalportal.application");
        }
    }
}
