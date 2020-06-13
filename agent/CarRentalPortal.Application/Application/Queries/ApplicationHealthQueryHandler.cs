using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Application.Queries
{
    public class ApplicationHealthQueryHandler : IRequestHandler<ApplicationHealthQuery, string>
    {
        public Task<string> Handle(ApplicationHealthQuery request, CancellationToken cancellationToken)
        {
            return Task.FromResult("Beep-boop. Application running.");
        }
    }
}
