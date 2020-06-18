using Microsoft.EntityFrameworkCore.Migrations;

namespace CarRentalPortal.Infrastructure.Persistence.Migrations.Identity
{
    public partial class AddUsernameFieldInUserEntity : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Username",
                schema: "carrentalportal.identity",
                table: "Users",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Users_Username",
                schema: "carrentalportal.identity",
                table: "Users",
                column: "Username",
                unique: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Users_Username",
                schema: "carrentalportal.identity",
                table: "Users");

            migrationBuilder.DropColumn(
                name: "Username",
                schema: "carrentalportal.identity",
                table: "Users");
        }
    }
}
