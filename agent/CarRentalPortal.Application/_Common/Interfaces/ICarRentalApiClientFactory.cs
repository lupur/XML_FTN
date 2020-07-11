using CarRentalAPI;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface ICarRentalApiClientFactory
    {
        CarRentalApiClient CreateChannel();
    }
}
