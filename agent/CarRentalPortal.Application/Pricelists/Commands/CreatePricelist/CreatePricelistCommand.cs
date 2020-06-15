using MediatR;

namespace CarRentalPortal.Application.Pricelists.Commands.CreatePricelist
{
    public class CreatePricelistCommand : IRequest<int>
    {
        public double BasePrice { get; set; }
        public double MileagePenaltyPrice { get; set; }
    }
}
