using CarRentalAPI;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.CreateUser
{
    public class CreateUserSoapCommandHandler : IRequestHandler<CreateUserSoapCommand, int>
    {
        private readonly BrandDetailsPort _channel;

        public CreateUserSoapCommandHandler(ICarRentalApiClientFactory _factory)
        {
            _channel = _factory.CreateChannel();
        }

        public async Task<int> Handle(CreateUserSoapCommand request, CancellationToken cancellationToken)
        {
            throw new System.NotImplementedException();
        }
    }
}
