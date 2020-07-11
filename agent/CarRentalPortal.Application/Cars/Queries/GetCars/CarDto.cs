using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Application.Reviews.Queries.GetReviews;
using CarRentalPortal.Core.Entities;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class CarDto : IMapFrom<Car>
    {
        public int Id { get; set; }
        [JsonProperty("carCategory")]
        public string CarCategoryName { get; set; }
        public string CarBrand { get; set; }
        public string CarModel { get; set; }
        public short ProductionYear { get; set; }
        public string FuelType { get; set; }
        public string TransmissionType { get; set; }
        public string Color { get; set; }
        public string Location { get; set; }
        public long Mileage { get; set; }
        public long? MileageConstraint { get; set; }
        public byte NumberOfSeats { get; set; }
        public double AverageRating { get; set; }
        public int OwnerId { get; set; }
        public string OwnerFullName { get; set; }
        public string OwnerContactInfo { get; set; }
        public ICollection<ReviewDto> Reviews { get; set; }
        public ICollection<CarImageDto> Images { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<Car, CarDto>()
                .ForMember(d => d.CarCategoryName, opt => opt.MapFrom(s => s.CarCategory.Name))
                .ForMember(d => d.CarBrand, opt => opt.MapFrom(s => s.CarBrandName))
                .ForMember(d => d.CarModel, opt => opt.MapFrom(s => s.CarModelName));
        }
    }
}
