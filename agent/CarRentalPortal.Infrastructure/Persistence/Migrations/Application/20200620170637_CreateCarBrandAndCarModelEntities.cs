using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Application
{
    public partial class CreateCarBrandAndCarModelEntities : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Brand",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropColumn(
                name: "CarModel",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.AddColumn<string>(
                name: "CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars",
                nullable: true);

            migrationBuilder.CreateTable(
                name: "CarBrands",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Name = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarBrands", x => x.Name);
                });

            migrationBuilder.CreateTable(
                name: "CarModels",
                schema: "carrentalportal.application",
                columns: table => new
                {
                    Name = table.Column<string>(nullable: false),
                    CarBrandName = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CarModels", x => x.Name);
                    table.ForeignKey(
                        name: "FK_CarModels_CarBrands_CarBrandName",
                        column: x => x.CarBrandName,
                        principalSchema: "carrentalportal.application",
                        principalTable: "CarBrands",
                        principalColumn: "Name",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Cars_CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarBrandName");

            migrationBuilder.CreateIndex(
                name: "IX_CarModels_CarBrandName",
                schema: "carrentalportal.application",
                table: "CarModels",
                column: "CarBrandName");

            migrationBuilder.AddForeignKey(
                name: "FK_Cars_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars",
                column: "CarBrandName",
                principalSchema: "carrentalportal.application",
                principalTable: "CarBrands",
                principalColumn: "Name",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Cars_CarBrands_CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropTable(
                name: "CarModels",
                schema: "carrentalportal.application");

            migrationBuilder.DropTable(
                name: "CarBrands",
                schema: "carrentalportal.application");

            migrationBuilder.DropIndex(
                name: "IX_Cars_CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.DropColumn(
                name: "CarBrandName",
                schema: "carrentalportal.application",
                table: "Cars");

            migrationBuilder.AddColumn<string>(
                name: "Brand",
                schema: "carrentalportal.application",
                table: "Cars",
                type: "text",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "CarModel",
                schema: "carrentalportal.application",
                table: "Cars",
                type: "text",
                nullable: true);
        }
    }
}
