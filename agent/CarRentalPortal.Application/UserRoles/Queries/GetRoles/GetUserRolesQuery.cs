using MediatR;

namespace CarRentalPortal.Application.UserRoles.Queries.GetRoles
{
    public class GetUserRolesQuery : IRequest<RoleVm>
    {
        public int UserId { get; set; }
    }
}
