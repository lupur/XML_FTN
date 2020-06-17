using MediatR;
using System.IO;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarImages.Commands.DeleteCarImages
{
    public class DeleteCarImagesCommandHandler : IRequestHandler<DeleteCarImagesCommand>
    {
        public async Task<Unit> Handle(DeleteCarImagesCommand request, CancellationToken cancellationToken)
        {
            if (!string.IsNullOrWhiteSpace(request.Path))
                await Task.Run(() => Directory.Delete(request.Path, true));

            return Unit.Value;
        }
    }
}
