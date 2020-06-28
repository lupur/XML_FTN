using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System;

namespace CarRentalPortal.Application.Rentals.Queries
{
    public class RentalDto : IMapFrom<Rental>
    {
        public int Id { get; set; }
        public int RentalBundleId { get; set; }
        public int CarId { get; set; }
        public string CarBrand { get; set; }
        public string CarModel { get; set; }
        public string CarYear { get; set; }
        public int CustomerId { get; set; }
        public string CustomerFullName { get; set; }
        public string CustomerContactInfo { get; set; }
        public DateTime PickupDate { get; set; }
        public DateTime ReturnDate { get; set; }
        public string Status { get; set; }
        public DateTime CreatedOn { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<Rental, RentalDto>()
                .ForMember(d => d.CarBrand, opt => opt.MapFrom(s => s.Car.CarBrandName))
                .ForMember(d => d.CarModel, opt => opt.MapFrom(s => s.Car.CarModelName))
                .ForMember(d => d.CarYear, opt => opt.MapFrom(s => s.Car.ProductionYear))
                .ForMember(d => d.PickupDate, opt => opt.MapFrom(s => s.StartDate))
                .ForMember(d => d.ReturnDate, opt => opt.MapFrom(s => s.EndDate))
                .ForMember(d => d.Status, opt => opt.MapFrom(s => s.RentalBundle.Status))
                .ForMember(d => d.CreatedOn, opt => opt.MapFrom(s => s.RentalBundle.CreatedOn));
        }
    }
}