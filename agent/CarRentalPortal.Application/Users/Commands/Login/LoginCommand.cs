using CarRentalPortal.Application.Users.Queries.GetUsers;
using MediatR;

namespace CarRentalPortal.Application.Users.Commands.Login
{
    public class LoginCommand : IRequest<LoginResultVm>
    {
        public string UsernameOrEmail { get; set; }
        public string Password { get; set; }
    }
}
