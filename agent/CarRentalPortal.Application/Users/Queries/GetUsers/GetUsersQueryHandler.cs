using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Queries.GetUsers
{
    public class GetUsersQueryHandler : IRequestHandler<GetUsersQuery, UserVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _identityContext;

        public GetUsersQueryHandler(IMapper mapper, IIdentityDbContext identityContext)
        {
            _mapper = mapper;
            _identityContext = identityContext;
        }

        public async Task<UserVm> Handle(GetUsersQuery request, CancellationToken cancellationToken)
        {
            return new UserVm
            {
                Users = await _identityContext.Users
                    .ProjectTo<UserDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
