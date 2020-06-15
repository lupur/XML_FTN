using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Reviews.Commands.CreateReview
{
    public class CreateReviewCommandHandler : IRequestHandler<CreateReviewCommand, int>
    {
        private readonly IApplicationDbContext _appContext;

        public CreateReviewCommandHandler(IApplicationDbContext appContext)
        {
            _appContext = appContext;
        }

        public async Task<int> Handle(CreateReviewCommand request, CancellationToken cancellationToken)
        {
            var entity = new Review
            {
                AuthorId = request.AuthorId,
                AuthorDisplayName = request.AuthorDisplayName,
                AuthorEmail = request.AuthorEmail,
                CarId = request.CarId,
                Rating = request.Rating,
                Comment = request.Comment
            };

            await _appContext.Reviews.AddAsync(entity);
            await _appContext.SaveChangesAsync(cancellationToken);

            return entity.Id;
        }
    }
}
