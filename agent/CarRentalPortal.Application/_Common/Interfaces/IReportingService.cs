using CarRentalPortal.Application.Dashboard.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface IReportingService
    {
        Task<byte[]> GenerateMileageReport(ICollection<MileageReportModel> model);
    }
}
