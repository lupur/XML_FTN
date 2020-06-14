using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateProductionYearDataType : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<short>(
                name: "ProductionYear",
                schema: "carrentalportal.application",
                table: "CarAds",
                nullable: false,
                oldClrType: typeof(byte),
                oldType: "tinyint");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<byte>(
                name: "ProductionYear",
                schema: "carrentalportal.application",
                table: "CarAds",
                type: "tinyint",
                nullable: false,
                oldClrType: typeof(short));
        }
    }
}
