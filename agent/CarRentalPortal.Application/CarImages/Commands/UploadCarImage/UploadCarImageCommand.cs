using MediatR;

namespace CarRentalPortal.Application.CarImages.Commands.UploadCarImage
{
    public class UploadCarImageCommand : IRequest<string>
    {
        public int CarId { get; set; }
        public string ImagePath { get; set; }
    }
}
