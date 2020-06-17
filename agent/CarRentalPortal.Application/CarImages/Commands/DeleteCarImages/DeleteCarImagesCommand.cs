using MediatR;

namespace CarRentalPortal.Application.CarImages.Commands.DeleteCarImages
{
    public class DeleteCarImagesCommand : IRequest
    {
        public string Path { get; set; }
    }
}
