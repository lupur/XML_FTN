using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using MediatR;

namespace CarRentalPortal.Application.Cars.Commands.CreateCar
{
    public class CreateCarSoapCommand : IRequest, IMapFrom<CreateCarCommand>
    {
        public int CarCategoryId { get; set; }
        public string BrandName { get; set; }
        public string ModelName { get; set; }
        public string FuelType { get; set; }
        public string TransmissionType { get; set; }
        public string Location { get; set; }
        public double Mileage { get; set; }
        public int NumberOfSeats { get; set; }
        public long UserId { get; set; }
        public double DailyPrice { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<CreateCarCommand, CreateCarSoapCommand>()
                .ForMember(d => d.BrandName, opt => opt.MapFrom(s => s.CarBrand))
                .ForMember(d => d.ModelName, opt => opt.MapFrom(s => s.CarModel))
                .ForMember(d => d.FuelType, opt => opt.MapFrom(s => s.FuelType))
                .ForMember(d => d.TransmissionType, opt => opt.MapFrom(s => s.TransmissionType))
                .ForMember(d => d.Mileage, opt => opt.MapFrom(s => (double)s.Mileage))
                .ForMember(d => d.UserId, opt => opt.MapFrom(s => (long)s.OwnerId));
        }
    }
}
