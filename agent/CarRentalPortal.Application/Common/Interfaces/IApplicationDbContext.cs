using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Common.Interfaces
{
    public interface IApplicationDbContext
    {
        DbSet<Car> Cars { get; set; }
        DbSet<CarImage> CarImages { get; set; }
        DbSet<Rental> Rentals { get; set; }
        DbSet<RentalBundle> RentalBundles { get; set; }

        Task<int> SaveChangesAsync(CancellationToken cancellationToken);
    }
}
