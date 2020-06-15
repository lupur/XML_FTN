using MediatR;

namespace CarRentalPortal.Application.Reviews.Commands.CreateReview
{
    public class CreateReviewCommand : IRequest<int>
    {
        public int AuthorId { get; set; }
        public string AuthorDisplayName { get; set; }
        public string AuthorEmail { get; set; }
        public int CarId { get; set; }
        public int Rating { get; set; }
        public string Comment { get; set; }
    }
}
