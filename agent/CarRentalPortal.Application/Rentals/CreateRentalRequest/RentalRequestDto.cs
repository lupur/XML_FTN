using CarRentalPortal.Application._Common.Mappings;
using CarRentalPortal.Core.Entities;
using System;

namespace CarRentalPortal.Application.Rentals.CreateRentalRequest
{
    public class RentalRequestDto : IMapFrom<Rental>
    {
        public int UserId { get; set; }
        public int CarId { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Remarks { get; set; }
    }
}