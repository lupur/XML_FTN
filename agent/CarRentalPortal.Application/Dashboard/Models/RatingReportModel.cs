using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.Dashboard.Models
{
    public class RatingReportModel : IMapFrom<Review>
    {
        public int CarId { get; set; }
        public string CarBrandName { get; set; }
        public string CarModelName { get; set; }
        public string OwnerFullName { get; set; }
        public string Reviewer { get; set; }
        public double Rating { get; set; }
        

        public void Mapping(Profile profile)
        {
            profile.CreateMap<Review, RatingReportModel>()
                .ForMember(d => d.CarBrandName, opt => opt.MapFrom(s => s.Car.CarBrandName))
                .ForMember(d => d.CarModelName, opt => opt.MapFrom(s => s.Car.CarModelName))
                .ForMember(d => d.OwnerFullName, opt => opt.MapFrom(s => s.Car.OwnerFullName))
                .ForMember(d => d.Reviewer, opt => opt.MapFrom(s => s.AuthorDisplayName));
        }
    }
}
