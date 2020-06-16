using MediatR;

namespace CarRentalPortal.Application.CarImages.Commands.UploadCarImage
{
    public class UploadCarImageCommand : IRequest<string>
    {
        public int CarAdId { get; set; }
        public string ImagePath { get; set; }
    }
}
