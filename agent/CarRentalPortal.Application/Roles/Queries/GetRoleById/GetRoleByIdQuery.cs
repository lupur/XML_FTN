using MediatR;

namespace CarRentalPortal.Application.Roles.Queries.GetRoleById
{
    public class GetRoleByIdQuery : IRequest<RoleDto>
    {
        public int Id { get; set; }
    }
}
