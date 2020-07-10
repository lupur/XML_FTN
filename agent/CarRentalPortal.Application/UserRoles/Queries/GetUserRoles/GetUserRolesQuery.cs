using CarRentalPortal.Application.Roles.Queries;
using MediatR;

namespace CarRentalPortal.Application.UserRoles.Queries.GetUserRoles
{
    public class GetUserRolesQuery : IRequest<RoleVm>
    {
        public int UserId { get; set; }
    }
}
