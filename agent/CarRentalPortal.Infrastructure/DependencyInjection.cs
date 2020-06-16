using CarRentalPortal.Application.Common.Interfaces;
using CarRentalPortal.Infrastructure.Persistence;
using CarRentalPortal.Infrastructure.Services;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace CarRentalPortal.Infrastructure
{
    public static class DependencyInjection
    {
        public static IServiceCollection AddInfrastructure(this IServiceCollection services, IConfiguration configuration)
        {
            services.AddDbContext<IdentityDbContext>(options =>
                options.UseMySQL(
                    configuration.GetConnectionString("IdentityConnection"),
                    opt => opt.MigrationsAssembly(typeof(IdentityDbContext).Assembly.FullName)));
            services.AddScoped<IIdentityDbContext>(provider => provider.GetService<IdentityDbContext>());

            services.AddDbContext<ApplicationDbContext>(options =>
                options.UseMySQL(
                    configuration.GetConnectionString("ApplicationConnection"),
                    opt => opt.MigrationsAssembly(typeof(ApplicationDbContext).Assembly.FullName)));
            services.AddScoped<IApplicationDbContext>(provider => provider.GetService<ApplicationDbContext>());

            services.AddTransient<IDataProtectionService, DataProtectionService>();

            return services;
        }
    }
}
