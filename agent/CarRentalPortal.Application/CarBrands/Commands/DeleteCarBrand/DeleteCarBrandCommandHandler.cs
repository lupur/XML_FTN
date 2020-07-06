using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarBrands.Commands.DeleteCarBrand
{
    public class DeleteCarBrandCommandHandler : IRequestHandler<DeleteCarBrandCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public DeleteCarBrandCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(DeleteCarBrandCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarBrands.FindAsync(request.Name);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarBrand), request.Name);
            }

            _appContext.CarBrands.Remove(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
