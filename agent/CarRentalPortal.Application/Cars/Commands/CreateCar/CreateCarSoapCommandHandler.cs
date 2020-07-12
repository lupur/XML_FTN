using CarRentalAPI;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Cars.Commands.CreateCar
{
    public class CreateCarSoapCommandHandler : IRequestHandler<CreateCarSoapCommand>
    {
        private readonly CarRentalApiClient _channel;
        private readonly IApplicationDbContext _context;

        public CreateCarSoapCommandHandler(ICarRentalApiClientFactory _factory, IApplicationDbContext context)
        {
            _channel = _factory.CreateChannel();
            _context = context;
        }

        public async Task<Unit> Handle(CreateCarSoapCommand request, CancellationToken cancellationToken)
        {
            var category = await _context.CarCategories.FindAsync(request.CarCategoryId);
            if (category == null)
                throw new NotFoundException(nameof(CarCategory), request.CarCategoryId);

            var addVehicleRequest = new AddNewVehicleRequest1
            {
                AddNewVehicleRequest = new AddNewVehicleRequest
                {
                    brandName = request.BrandName,
                    modelName = request.ModelName,
                    fuelTypeName = request.FuelType,
                    transmissionTypeName = request.TransmissionType,
                    location = request.Location,
                    noOfSeats = request.NumberOfSeats,
                    mileage = request.Mileage,
                    userId = request.UserId,
                    dailyPrice = category.RentalValue
                }
            };

            try
            {
                var promise = _channel.AddNewVehicleAsync(addVehicleRequest);
                var result = await promise;

                var id = result.AddNewVehicleResponse.vehicleId;
            }
            catch (Exception ex)
            {
                throw ex;
            }

            return Unit.Value;
        }
    }
}
