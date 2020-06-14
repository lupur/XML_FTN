using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateCarAdEntityWithAgentContactInfo : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "CarAds");

            migrationBuilder.AddColumn<string>(
                name: "AgentContactInfo",
                schema: "carrentalportal.application",
                table: "CarAds",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "AgentId",
                schema: "carrentalportal.application",
                table: "CarAds",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "AgentContactInfo",
                schema: "carrentalportal.application",
                table: "CarAds");

            migrationBuilder.DropColumn(
                name: "AgentId",
                schema: "carrentalportal.application",
                table: "CarAds");

            migrationBuilder.AddColumn<int>(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "CarAds",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }
    }
}
