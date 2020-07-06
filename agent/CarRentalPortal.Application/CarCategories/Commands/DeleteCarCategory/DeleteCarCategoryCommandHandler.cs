using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarCategories.Commands.DeleteCarCategory
{
    public class DeleteCarCategoryCommandHandler : IRequestHandler<DeleteCarCategoryCommand>
    {
        private readonly IApplicationDbContext _appContext;

        public DeleteCarCategoryCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<Unit> Handle(DeleteCarCategoryCommand request, CancellationToken cancellationToken)
        {
            var entity = await _appContext.CarCategories.FindAsync(request.Id);
            if (entity == null)
            {
                throw new NotFoundException(nameof(CarCategory), request.Id);
            }

            _appContext.CarCategories.Remove(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return Unit.Value;
        }
    }
}
