using CarRentalPortal.Infrastructure.Persistence.Configurations;
using Microsoft.EntityFrameworkCore;

namespace CarRentalPortal.Infrastructure.Persistence.Extensions
{
    public static class ModelBuilderExtensions
    {
        public static ModelBuilder ApplyIdentityConfigurations(this ModelBuilder builder)
        {
            builder.ApplyConfiguration(new UsersConfiguration());
            builder.ApplyConfiguration(new RolesConfiguration());
            builder.ApplyConfiguration(new UserRolesConfiguration());

            return builder;
        }

        public static ModelBuilder ApplyApplicationConfigurations(this ModelBuilder builder)
        {
            builder.ApplyConfiguration(new CarsConfiguration());
            builder.ApplyConfiguration(new CarCategoriesConfiguration());
            builder.ApplyConfiguration(new CarImagesConfiguration());
            builder.ApplyConfiguration(new RentalsConfiguration());
            builder.ApplyConfiguration(new RentalBundlesConfiguration());

            return builder;
        }
    }
}
