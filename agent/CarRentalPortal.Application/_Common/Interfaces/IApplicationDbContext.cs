using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface IApplicationDbContext
    {
        DbSet<Car> Cars { get; set; }
        DbSet<CarBrand> CarBrands { get; set; }
        DbSet<CarModel> CarModels { get; set; }
        DbSet<CarCategory> CarCategories { get; set; }
        DbSet<CarImage> CarImages { get; set; }
        DbSet<Rental> Rentals { get; set; }
        DbSet<RentalBundle> RentalBundles { get; set; }
        DbSet<Review> Reviews { get; set; }

        Task<int> SaveChangesAsync(CancellationToken cancellationToken);
    }
}
