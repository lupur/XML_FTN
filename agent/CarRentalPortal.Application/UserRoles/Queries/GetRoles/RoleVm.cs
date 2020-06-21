using System.Collections;
using System.Collections.Generic;

namespace CarRentalPortal.Application.UserRoles.Queries.GetRoles
{
    public class RoleVm
    {
        public ICollection<RoleDto> Roles { get; set; }
    }
}