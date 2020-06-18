using MediatR;

namespace CarRentalPortal.Application.Users.Commands.Login
{
    public class LoginCommand : IRequest<string>
    {
        public string UsernameOrEmail { get; set; }
        public string Password { get; set; }
    }
}
