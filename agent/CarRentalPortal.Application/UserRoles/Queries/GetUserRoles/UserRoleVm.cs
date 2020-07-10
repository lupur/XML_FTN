using System.Collections.Generic;

namespace CarRentalPortal.Application.UserRoles.Queries.GetUserRoles
{
    public class UserRoleVm
    {
        public ICollection<UserRoleDto> Roles { get; set; }
    }
}