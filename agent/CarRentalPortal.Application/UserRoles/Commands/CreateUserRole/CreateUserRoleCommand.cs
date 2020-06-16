using MediatR;

namespace CarRentalPortal.Application.UserRoles.Commands.CreateUserRole
{
    public class CreateUserRoleCommand : IRequest<(int, int)>
    {
        public int UserId { get; set; }
        public int RoleId { get; set; }
    }
}
