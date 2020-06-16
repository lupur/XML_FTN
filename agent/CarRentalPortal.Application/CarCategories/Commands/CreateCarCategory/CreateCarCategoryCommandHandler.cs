using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory
{
    public class CreateCarCategoryCommandHandler : IRequestHandler<CreateCarCategoryCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateCarCategoryCommandHandler(IApplicationDbContext dbContext)
        {
            _appContext = dbContext;
        }

        public async Task<int> Handle(CreateCarCategoryCommand request, CancellationToken cancellationToken)
        {
            var entity = new CarCategory
            {
                Name = request.Name,
                RentalValue = request.RentalValue
            };

            _appContext.CarCategories.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
