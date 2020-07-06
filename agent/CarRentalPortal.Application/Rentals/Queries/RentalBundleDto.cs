using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using CarRentalPortal.Core.Enums;
using System;
using System.Collections.Generic;

namespace CarRentalPortal.Application.Rentals.Queries
{
    public class RentalBundleDto : IMapFrom<RentalBundle>
    {
        public int Id { get; set; }
        public int NumberOfItems { get; set; }
        public RentalStatus Status { get; set; }
        public DateTime CreatedOn { get; set; }
        public ICollection<RentalDto> Rentals { get; set; }
    }
}
