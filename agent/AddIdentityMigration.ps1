$MigrationName = $args[0] 

Add-Migration `
    -Name $MigrationName -Context IdentityDbContext `
    -OutputDir Persistence/Migrations/Identity `
    -Project CarRentalPortal.Infrastructure `
    -StartupProject CarRentalPortal.API `
    -Verbose 