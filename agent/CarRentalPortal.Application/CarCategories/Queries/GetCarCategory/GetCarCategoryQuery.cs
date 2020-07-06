using CarRentalPortal.Application.CarCategories.Queries.GetCarCategories;
using MediatR;

namespace CarRentalPortal.Application.CarCategories.Queries.GetCarCategory
{
    public class GetCarCategoryQuery : IRequest<CarCategoryDto>
    {
        public int Id { get; set; }
    }
}
