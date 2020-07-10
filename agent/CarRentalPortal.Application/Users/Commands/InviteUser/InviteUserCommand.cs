using CarRentalPortal.Application.Roles.Queries;
using CarRentalPortal.Core.Enums;
using MediatR;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Users.Commands.InviteUser
{
    public class InviteUserCommand : IRequest<int>
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public AccountStatus Status { get; set; }
        public ICollection<RoleDto> Roles { get; set; }
    }
}
