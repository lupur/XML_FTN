using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateCarAndReviewEntity : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "AverageRating",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.AddColumn<int>(
                name: "Status",
                schema: "carrentalportal.application",
                table: "Reviews",
                nullable: false,
                defaultValue: 1);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Status",
                schema: "carrentalportal.application",
                table: "Reviews");

            migrationBuilder.AddColumn<double>(
                name: "AverageRating",
                schema: "carrentalportal.application",
                table: "Cars",
                type: "double",
                nullable: true);
        }
    }
}
