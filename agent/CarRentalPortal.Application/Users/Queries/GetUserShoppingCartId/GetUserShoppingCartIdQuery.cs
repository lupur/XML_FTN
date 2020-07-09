using MediatR;

namespace CarRentalPortal.Application.Users.Queries.GetUserShoppingCartId
{
    public class GetUserShoppingCartIdQuery : IRequest<int>
    {
        public int UserId { get; set; }
    }
}
