﻿using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface IIdentityDbContext
    {
        DbSet<User> Users { get; set; }
        DbSet<Role> Roles { get; set; }
        DbSet<UserRole> UserRoles { get; set; }

        Task<int> SaveChangesAsync(CancellationToken cancellationToken);
    }
}
