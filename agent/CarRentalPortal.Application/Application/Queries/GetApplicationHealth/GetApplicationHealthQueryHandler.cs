using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Application.Queries.GetApplicationHealth
{
    public class GetApplicationHealthQueryHandler : IRequestHandler<GetApplicationHealthQuery, string>
    {
        public Task<string> Handle(GetApplicationHealthQuery request, CancellationToken cancellationToken)
        {
            return Task.FromResult("Beep-boop. Application running.");
        }
    }
}
