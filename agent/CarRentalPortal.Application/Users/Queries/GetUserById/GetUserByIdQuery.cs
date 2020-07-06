using CarRentalPortal.Application.Users.Queries.GetUsers;
using MediatR;

namespace CarRentalPortal.Application.Users.Queries.GetUserById
{
    public class GetUserByIdQuery : IRequest<UserDto>
    {
        public int Id { get; set; }
    }
}
