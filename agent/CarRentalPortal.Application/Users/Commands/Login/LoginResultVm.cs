using CarRentalPortal.Application.UserRoles.Queries.GetRoles;
using System;
using System.Collections.Generic;
using System.Text;

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
        public ICollection<RoleDto> Roles { get; set; }
    }
}
