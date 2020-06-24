using MediatR;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest
{
    public class CreateRentalRequestCommand : IRequest<int>
    {
        public ICollection<RentalRequestDto> Rentals { get; set; }
    }
}
