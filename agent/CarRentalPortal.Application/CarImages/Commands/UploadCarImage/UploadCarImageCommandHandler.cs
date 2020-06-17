using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarImages.Commands.UploadCarImage
{
    public class UploadCarImageCommandHandler : IRequestHandler<UploadCarImageCommand, string>
    {
        private readonly IApplicationDbContext _appContext;

        public UploadCarImageCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<string> Handle(UploadCarImageCommand request, CancellationToken cancellationToken)
        {
            var entity = new CarImage
            {
                CarAdId = request.CarId,
                Uri = request.ImagePath
            };

            _appContext.CarImages.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Uri;
        }
    }
}
