using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class UpdateShoppingCartItem : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "ShoppingCartItems",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "OwnerId",
                schema: "carrentalportal.application",
                table: "ShoppingCartItems");
        }
    }
}
