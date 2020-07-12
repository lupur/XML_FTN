using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.Dashboard.Models
{
    public class CommentReportModel : IMapFrom<Review>
    {
        public int CarId { get; set; }
        public string CarBrand { get; set; }
        public string CarModel { get; set; }
        public string CarOwner { get; set; }
        public string Author { get; set; }
        public string AuthorContactInfo { get; set; }
        public string Comment { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<Review, CommentReportModel>()
                .ForMember(d => d.CarBrand, opt => opt.MapFrom(s => s.Car.CarBrandName))
                .ForMember(d => d.CarModel, opt => opt.MapFrom(s => s.Car.CarModelName))
                .ForMember(d => d.CarOwner, opt => opt.MapFrom(s => s.Car.OwnerFullName))
                .ForMember(d => d.Author, opt => opt.MapFrom(s => s.AuthorDisplayName))
                .ForMember(d => d.AuthorContactInfo, opt => opt.MapFrom(s => s.AuthorEmail));
        }
    }
}
