namespace CarRentalPortal.Core.Entities
{
    public class CarImage
    {
        public int Id { get; set; }
        public string Uri { get; set; }
        public int CarAdId { get; set; }
        public Car CarAd { get; set; }
    }
}
