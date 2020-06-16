$MigrationName = $args[0] 

Add-Migration `
    -Name $MigrationName `
    -Context ApplicationDbContext `
    -OutputDir Persistence/Migrations/Application `
    -Project CarRentalPortal.Infrastructure `
    -StartupProject CarRentalPortal.API `
    -Verbose