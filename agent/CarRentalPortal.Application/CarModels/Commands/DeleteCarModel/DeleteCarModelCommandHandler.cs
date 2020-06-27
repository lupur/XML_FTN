using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarModels.Commands.DeleteCarModel
{
    public class DeleteCarModelCommandHandler : IRequestHandler<DeleteCarModelCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public DeleteCarModelCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(DeleteCarModelCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarModels.FindAsync(request.Name);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarModel), request.Name);
            }

            _appContext.CarModels.Remove(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
