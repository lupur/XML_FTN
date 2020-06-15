using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class AddCarCategories : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Cars_CarCategory_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropPrimaryKey(
                name: "PK_CarCategory",
                schema: "carrentalportal.application",
                table: "CarCategory");

            migrationBuilder.RenameTable(
                name: "CarCategory",
                schema: "carrentalportal.application",
                newName: "CarCategories",
                newSchema: "carrentalportal.application");

            migrationBuilder.AddPrimaryKey(
                name: "PK_CarCategories",
                schema: "carrentalportal.application",
                table: "CarCategories",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Cars_CarCategories_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarCategoryId",
                principalSchema: "carrentalportal.application",
                principalTable: "CarCategories",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Cars_CarCategories_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropPrimaryKey(
                name: "PK_CarCategories",
                schema: "carrentalportal.application",
                table: "CarCategories");

            migrationBuilder.RenameTable(
                name: "CarCategories",
                schema: "carrentalportal.application",
                newName: "CarCategory",
                newSchema: "carrentalportal.application");

            migrationBuilder.AddPrimaryKey(
                name: "PK_CarCategory",
                schema: "carrentalportal.application",
                table: "CarCategory",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Cars_CarCategory_CarCategoryId",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarCategoryId",
                principalSchema: "carrentalportal.application",
                principalTable: "CarCategory",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
