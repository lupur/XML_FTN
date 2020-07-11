using CarRentalAPI;
using CarRentalPortal.Application._Common.Interfaces;

namespace CarRentalPortal.Infrastructure.Services
{
    public class CarRentalApiClientFactory : ICarRentalApiClientFactory
    {
        public CarRentalApiClient CreateChannel()
        {
            return new CarRentalApiClientClient();
        }
    }
}
