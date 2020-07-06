using MediatR;

namespace CarRentalPortal.Application.CarCategories.Commands.DeleteCarCategory
{
    public class DeleteCarCategoryCommand : IRequest
    {
        public int Id { get; set; }
    }
}
