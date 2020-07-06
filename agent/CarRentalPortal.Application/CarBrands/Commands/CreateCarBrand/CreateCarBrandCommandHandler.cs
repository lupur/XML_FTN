using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.CarBrands.Commands.CreateCarBrand
{
    public class CreateCarBrandCommandHandler : IRequestHandler<CreateCarBrandCommand, string>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateCarBrandCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<string> Handle(CreateCarBrandCommand request, CancellationToken cancellationToken)
        {
            var entity = new CarBrand
            {
                Name = request.Name
            };

            _appContext.CarBrands.Add(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Name;
        }
    }
}
