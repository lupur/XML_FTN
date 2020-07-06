using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarModels.Commands.CreateCarModel
{
    public class CreateCarModelCommandHandler : IRequestHandler<CreateCarModelCommand, string>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateCarModelCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<string> Handle(CreateCarModelCommand request, CancellationToken cancellationToken)
        {
            var entity = new CarModel
            {
                Name = request.Name,
                CarBrandName = request.BrandName
            };

            await _appContext.CarModels.AddAsync(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Name;
        }
    }
}
