using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.Roles.Queries
{
    public class RoleDto : IMapFrom<Role>
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }
}
