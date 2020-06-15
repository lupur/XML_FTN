using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateCarCategoryEntityWithRentalValue : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<double>(
                name: "RentalValue",
                schema: "carrentalportal.application",
                table: "CarCategory",
                nullable: false,
                defaultValue: 0.0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "RentalValue",
                schema: "carrentalportal.application",
                table: "CarCategory");
        }
    }
}
