using CarRentalPortal.Core.Entities;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Common.Interfaces
{
    public interface IApplicationDbContext
    {
        DbSet<CarAd> CarAds { get; set; }

        Task<int> SaveChangesAsync(CancellationToken cancellationToken);
    }
}
