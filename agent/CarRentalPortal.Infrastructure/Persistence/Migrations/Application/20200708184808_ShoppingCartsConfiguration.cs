using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class ShoppingCartsConfiguration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<int>(
                name: "Status",
                schema: "carrentalportal.application",
                table: "ShoppingCartItems",
                nullable: false,
                defaultValue: 1,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.CreateIndex(
                name: "IX_ShoppingCarts_UserId",
                schema: "carrentalportal.application",
                table: "ShoppingCarts",
                column: "UserId",
                unique: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_ShoppingCarts_UserId",
                schema: "carrentalportal.application",
                table: "ShoppingCarts");

            migrationBuilder.AlterColumn<int>(
                name: "Status",
                schema: "carrentalportal.application",
                table: "ShoppingCartItems",
                type: "int",
                nullable: false,
                oldClrType: typeof(int),
                oldDefaultValue: 1);
        }
    }
}
