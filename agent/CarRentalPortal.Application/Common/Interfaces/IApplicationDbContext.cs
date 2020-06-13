using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;

namespace CarRentalPortal.Application.Common.Interfaces
{
    public interface IApplicationDbContext
    {
        DbSet<User> Users { get; set; }
        DbSet<Role> Roles { get; set; }
    }
}
