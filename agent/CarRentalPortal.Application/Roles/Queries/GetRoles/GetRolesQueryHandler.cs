using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Roles.Queries.GetRoles
{
    public class GetRolesQueryHandler : IRequestHandler<GetRolesQuery, RoleVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _context;

        public GetRolesQueryHandler(IMapper mapper, IIdentityDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<RoleVm> Handle(GetRolesQuery request, CancellationToken cancellationToken)
        {
            return new RoleVm
            {
                Roles = await _context.Roles
                    .ProjectTo<RoleDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
