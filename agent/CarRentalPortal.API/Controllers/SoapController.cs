using CarRentalService;
using Microsoft.AspNetCore.Mvc;
using System;
using System.ServiceModel;
using System.Threading.Tasks;

namespace CarRentalPortal.API.Controllers
{
    public class SoapController : AbstractApiController
    {
        [HttpGet("test")]
        public async Task<ActionResult> Test()
        {
            BasicHttpBinding binding = null;
            EndpointAddress remoteAddres = null;
            ChannelFactory<BrandDetailsPortChannel> factory = null;
            BrandDetailsPortChannel serviceProxy = null;

            try
            {
                binding = new BasicHttpBinding(BasicHttpSecurityMode.TransportCredentialOnly);
                binding.Security.Transport.ClientCredentialType = HttpClientCredentialType.Ntlm;

                remoteAddres = new EndpointAddress(new Uri("http://localhost:8080/soap/service/brandDetailsWsdl.wsdl"));
                factory = new ChannelFactory<BrandDetailsPortChannel>(binding, remoteAddres);

                Task<BrandDetailsResponse1> task;
                serviceProxy = factory.CreateChannel();
                using (var scope = new OperationContextScope(serviceProxy))
                {
                    var request = new BrandDetailsRequest1
                    {
                        BrandDetailsRequest = new BrandDetailsRequest { id = 1 }
                    };
                    task = serviceProxy.BrandDetailsAsync(request);
                }

                var result = await task;
            }
            catch (Exception ex)
            {
                throw ex;
            }


            return null;
        }
    }
}
