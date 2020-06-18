using AutoMapper;

namespace CarRentalPortal.Application._Common.Mappings
{
    public interface IMapFrom<TSource>
    {
        void Mapping(Profile profile) => profile.CreateMap(typeof(TSource), GetType());
    }
}
