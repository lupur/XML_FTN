namespace CarRentalPortal.Application.Dashboard.Models
{
    public class CarReportModel
    {
        public string FileName { get; set; }
        public string ContentType => "application/pdf";
        public byte[] Content { get; set; }
    }
}
