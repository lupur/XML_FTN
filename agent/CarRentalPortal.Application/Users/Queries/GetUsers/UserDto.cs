using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Application.UserRoles.Queries.GetRoles;
using CarRentalPortal.Core.Entities;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Users.Queries.GetUsers
{
    public class UserDto : IMapFrom<User>
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Token { get; set; }
        public ICollection<RoleDto> Roles { get; set; }
    }
}