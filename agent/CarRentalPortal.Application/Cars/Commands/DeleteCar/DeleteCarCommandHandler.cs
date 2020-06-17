using CarRentalPortal.Application.Common.Exceptions;
using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Cars.Commands.DeleteCar
{
    public class DeleteCarCommandHandler : IRequestHandler<DeleteCarCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public DeleteCarCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(DeleteCarCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.Cars
                .SingleOrDefaultAsync(c => c.Id == request.CarId, cancellationToken);

            if (entity == null)
            {
                throw new NotFoundException(nameof(Car), request.CarId);
            }

            _appContext.Cars.Remove(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
