using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarAds.Commands.CreateCarAd
{
    public class CreateCarAdCommandHandler : IRequestHandler<CreateCarAdCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateCarAdCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<int> Handle(CreateCarAdCommand request, CancellationToken cancellationToken)
        {
            var entity = new CarAd
            {
                AgentId = request.AgentId,
                AgentContactInfo = request.AgentContactInfo,
                Brand = request.Brand,
                CarModel = request.CarModel,
                ProductionYear = request.ProductionYear,
                SegmentType = request.SegmentType,
                FuelType = request.FuelType,
                TransmissionType = request.TransmissionType,
                Color = request.Color,
                Mileage = request.Mileage,
                MileageConstraint = request.MileageConstraint,
                NumberOfSeats = request.NumberOfSeats
            };

            _appContext.CarAds.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
