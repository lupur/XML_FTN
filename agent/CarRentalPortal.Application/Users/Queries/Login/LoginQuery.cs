using MediatR;

namespace CarRentalPortal.Application.Users.Queries.Login
{
    public class LoginQuery : IRequest<LoginResultVm>
    {
        public string UsernameOrEmail { get; set; }
        public string Password { get; set; }
    }
}
