using MediatR;

namespace CarRentalPortal.Application.CarCategories.Commands.UpdateCarCategory
{
    public class UpdateCarCategoryCommand : IRequest
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public double RentalValue { get; set; }
    }
}
