using CarRentalPortal.Core.Enums;
using MediatR;

namespace CarRentalPortal.Application.Users.Commands.UpdateUserStatus
{
    public class UpdateUserStatusCommand : IRequest
    {
        public int Id { get; set; }
        public AccountStatus Status { get; set; }
    }
}
