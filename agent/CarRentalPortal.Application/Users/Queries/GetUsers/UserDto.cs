using AutoMapper;
using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Application.Roles.Queries;
using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Users.Queries.GetUsers
{
    public class UserDto : IMapFrom<User>
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public int ShoppingCartId { get; set; }
        public string Token { get; set; }
        public AccountStatus Status { get; set; }
        public ICollection<RoleDto> Roles { get; set; }

        public void Mapping(Profile profile)
        {
            profile.CreateMap<User, UserDto>()
                .ForMember(d => d.Roles, opt => opt.Ignore());
        }
    }
}