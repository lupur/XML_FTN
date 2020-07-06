using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateRentalEntity : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "UserId",
                schema: "carrentalportal.application",
                table: "Rentals");

            migrationBuilder.DropColumn(
                name: "AgentContactInfo",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropColumn(
                name: "AgentId",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.AddColumn<int>(
                name: "CustomerId",
                schema: "carrentalportal.application",
                table: "Rentals",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "OwnerContactInfo",
                schema: "carrentalportal.application",
                table: "Cars",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "OwnerFullName",
                schema: "carrentalportal.application",
                table: "Cars",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "Cars",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CustomerId",
                schema: "carrentalportal.application",
                table: "Rentals");

            migrationBuilder.DropColumn(
                name: "OwnerContactInfo",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropColumn(
                name: "OwnerFullName",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropColumn(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.AddColumn<int>(
                name: "UserId",
                schema: "carrentalportal.application",
                table: "Rentals",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<string>(
                name: "AgentContactInfo",
                schema: "carrentalportal.application",
                table: "Cars",
                type: "text",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "AgentId",
                schema: "carrentalportal.application",
                table: "Cars",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }
    }
}
