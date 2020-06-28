using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System;

namespace CarRentalPortal.Application.Rentals.Commands.CreateRentalRequest
{
    public class RentalRequestDto : IMapFrom<Rental>
    {
        public int OwnerId { get; set; }
        public int CustomerId { get; set; }
        public int CarId { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Remarks { get; set; }
        public bool IsBundle { get; set; }
    }
}