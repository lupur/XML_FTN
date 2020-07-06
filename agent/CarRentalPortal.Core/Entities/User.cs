using CarRentalPortal.Core.Enums;
using System.Collections.Generic;

namespace CarRentalPortal.Core.Entities
{
    public class User
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string Salt { get; set; }
        public AccountStatus Status { get; set; }
        public ICollection<UserRole> Roles { get; set; }
    }
}
