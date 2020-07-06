using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.UserRoles.Queries.GetRoles
{
    public class RoleDto : IMapFrom<Role>, IMapFrom<UserRole>
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<UserRole, RoleDto>()
                .ForMember(d => d.Id, opt => opt.MapFrom(s => s.RoleId))
                .ForMember(d => d.Name, opt => opt.MapFrom(s => s.Role.Name));
        }
    }
}
