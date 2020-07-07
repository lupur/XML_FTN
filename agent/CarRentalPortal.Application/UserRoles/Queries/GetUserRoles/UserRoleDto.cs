using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;

namespace CarRentalPortal.Application.UserRoles.Queries.GetUserRoles
{
    public class UserRoleDto : IMapFrom<UserRole>
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<UserRole, UserRoleDto>()
                .ForMember(d => d.Id, opt => opt.MapFrom(s => s.RoleId))
                .ForMember(d => d.Name, opt => opt.MapFrom(s => s.Role.Name));
        }
    }
}
