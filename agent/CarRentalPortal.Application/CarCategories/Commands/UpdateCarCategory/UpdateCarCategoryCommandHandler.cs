using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarCategories.Commands.UpdateCarCategory
{
    public class UpdateCarCategoryCommandHandler : IRequestHandler<UpdateCarCategoryCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public UpdateCarCategoryCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(UpdateCarCategoryCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarCategories.FindAsync(request.Id);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarCategory), request.Id);
            }

            entity.Name = request.Name;
            entity.RentalValue = request.RentalValue;

            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
