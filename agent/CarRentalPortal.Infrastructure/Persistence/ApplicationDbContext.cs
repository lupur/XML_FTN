using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;

namespace CarRentalPortal.Infrastructure.Persistence
{
    public class ApplicationDbContext : IApplicationDbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Role> Roles { get; set; }
    }
}
