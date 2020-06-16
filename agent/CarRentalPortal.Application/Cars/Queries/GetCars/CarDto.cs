using AutoMapper;
using CarRentalPortal.Application.Common.Mappings;
using CarRentalPortal.Core.Entities;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Cars.Queries.GetCars
{
    public class CarDto : IMapFrom<Car>
    {
        public int Id { get; set; }
        public int AgentId { get; set; }
        public string AgentContactInfo { get; set; }
        public string CarCategoryName { get; set; }
        public string Brand { get; set; }
        public string CarModel { get; set; }
        public short ProductionYear { get; set; }
        public string FuelType { get; set; }
        public string TransmissionType { get; set; }
        public string Color { get; set; }
        public string Location { get; set; }
        public long Mileage { get; set; }
        public long? MileageConstraint { get; set; }
        public byte NumberOfSeats { get; set; }
        public float AverageRating { get; set; }
        public ICollection<CarImageDto> Images { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<Car, CarDto>()
                .ForMember(d => d.CarCategoryName, opt => opt.MapFrom(s => s.CarCategory.Name));
        }
    }
}
