using System.Collections.Generic;

namespace CarRentalPortal.Application.Roles.Queries
{
    public class RoleVm
    {
        public ICollection<RoleDto> Roles { get; set; }
    }
}
