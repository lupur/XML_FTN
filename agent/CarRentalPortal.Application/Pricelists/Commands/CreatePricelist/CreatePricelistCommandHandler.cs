using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Pricelists.Commands.CreatePricelist
{
    public class CreatePricelistCommandHandler : IRequestHandler<CreatePricelistCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreatePricelistCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<int> Handle(CreatePricelistCommand request, CancellationToken cancellationToken)
        {
            var entity = new Pricelist
            {
                BasePrice = request.BasePrice,
                MileagePenaltyPrice = request.MileagePenaltyPrice
            };

            _appContext.Pricelists.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
