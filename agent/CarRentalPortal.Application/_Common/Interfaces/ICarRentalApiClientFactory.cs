using CarRentalAPI;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface ICarRentalApiClientFactory
    {
        BrandDetailsPort CreateChannel();
    }
}
