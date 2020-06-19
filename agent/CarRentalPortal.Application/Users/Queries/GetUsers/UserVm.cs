using System.Collections.Generic;

namespace CarRentalPortal.Application.Users.Queries.GetUsers
{
    public class UserVm
    {
        public ICollection<UserDto> Users { get; set; }
    }
}
