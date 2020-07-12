using CarRentalAPI;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Commands.CreateUser
{
    public class CreateUserSoapCommandHandler : IRequestHandler<CreateUserSoapCommand, long>
    {
        private readonly CarRentalApiClient _channel;

        public CreateUserSoapCommandHandler(ICarRentalApiClientFactory _factory)
        {
            _channel = _factory.CreateChannel();
        }

        public async Task<long> Handle(CreateUserSoapCommand request, CancellationToken cancellationToken)
        {
            var registerAgentRequest = new RegisterAgentRequest1
            {
                RegisterAgentRequest = new RegisterAgentRequest
                {
                    username = request.Username,
                    email = request.Email,
                    password = request.Password,
                    confirmPassword = request.ConfirmPassword
                }
            };

            try
            {
                var promise = _channel.RegisterAgentAsync(registerAgentRequest);
                var result = await promise;

                return result.RegisterAgentResponse.id;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
