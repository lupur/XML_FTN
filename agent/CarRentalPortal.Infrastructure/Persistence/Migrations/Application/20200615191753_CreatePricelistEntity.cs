using Microsoft.EntityFrameworkCore.Migrations;
using MySql.Data.EntityFrameworkCore.Metadata;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class CreatePricelistEntity : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.CreateTable(
                name: "Pricelists",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    BasePrice = table.Column<double>(nullable: false),
                    MileagePenaltyPrice = table.Column<double>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Pricelists", x => x.Id);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CarAds_PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds",
                column: "PricelistId");

            migrationBuilder.AddForeignKey(
                name: "FK_CarAds_Pricelists_PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds",
                column: "PricelistId",
                principalSchema: "carrentalportal.application",
                principalTable: "Pricelists",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CarAds_Pricelists_PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds");

            migrationBuilder.DropTable(
                name: "Pricelists",
                schema: "carrentalportal.application");

            migrationBuilder.DropIndex(
                name: "IX_CarAds_PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds");

            migrationBuilder.DropColumn(
                name: "PricelistId",
                schema: "carrentalportal.application",
                table: "CarAds");
        }
    }
}
