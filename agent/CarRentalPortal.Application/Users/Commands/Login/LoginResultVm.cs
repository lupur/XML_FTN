using CarRentalPortal.Application.UserRoles.Queries.GetUserRoles;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Users.Commands.Login
{
    public class LoginResultVm
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Token { get; set; }
        public ICollection<UserRoleDto> Roles { get; set; }
    }
}
