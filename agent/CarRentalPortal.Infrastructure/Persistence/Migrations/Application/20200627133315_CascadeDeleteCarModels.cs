using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class CascadeDeleteCarModels : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CarModels_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels");

            migrationBuilder.AddForeignKey(
                name: "FK_CarModels_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels",
                column: "CarBrandName",
                principalSchema: "carrentalportal.application",
                principalTable: "CarBrands",
                principalColumn: "Name",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_CarModels_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels");

            migrationBuilder.AddForeignKey(
                name: "FK_CarModels_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels",
                column: "CarBrandName",
                principalSchema: "carrentalportal.application",
                principalTable: "CarBrands",
                principalColumn: "Name",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
