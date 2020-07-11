using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Cars.Commands.CreateCar
{
    public class CreateCarCommandHandler : IRequestHandler<CreateCarCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateCarCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<int> Handle(CreateCarCommand request, CancellationToken cancellationToken)
        {
            var entity = new Car
            {
                CarBrandName = request.CarBrand,
                CarModelName = request.CarModel,
                CarCategoryId = request.CarCategoryId,
                ProductionYear = request.ProductionYear,
                FuelType = request.FuelType,
                TransmissionType = request.TransmissionType,
                Location = request.Location,
                Color = request.Color,
                Mileage = request.Mileage,
                MileageConstraint = request.MileageConstraint,
                NumberOfSeats = request.NumberOfSeats,
                OwnerId = request.OwnerId,
                OwnerFullName = request.OwnerFullName,
                OwnerContactInfo = request.OwnerContactInfo
            };

            _appContext.Cars.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
