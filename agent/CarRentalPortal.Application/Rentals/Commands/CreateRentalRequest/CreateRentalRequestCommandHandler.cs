using AutoMapper;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest
{
    public class CreateRentalRequestCommandHandler : IRequestHandler<CreateRentalRequestCommand, int>
    {
        private readonly IMapper _mapper;
        private readonly IApplicationDbContext _appContext;

        public CreateRentalRequestCommandHandler(IMapper mapper, IApplicationDbContext appContext)
        {
            _mapper = mapper;
            _appContext = appContext;
        }

        public async Task<int> Handle(CreateRentalRequestCommand request, CancellationToken cancellationToken)
        {
            var entity = new RentalBundle
            {
                NumberOfItems = request.Rentals.Count
            };

            foreach (var rental in request.Rentals)
            {
                entity.Rentals.Add(new Rental
                {
                    CarId = rental.CarId,
                    UserId = rental.UserId,
                    StartDate = rental.StartDate,
                    EndDate = rental.EndDate,
                    Remarks = rental.Remarks
                });
            }

            await _appContext.RentalBundles.AddAsync(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
