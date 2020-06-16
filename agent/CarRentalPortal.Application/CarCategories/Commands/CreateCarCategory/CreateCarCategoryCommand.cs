using MediatR;

namespace CarRentalPortal.Application.CarCategories.Commands.CreateCarCategory
{
    public class CreateCarCategoryCommand : IRequest<int>
    {
        public string Name { get; set; }
        public double RentalValue { get; set; }
    }
}
