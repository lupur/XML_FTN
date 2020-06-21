using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Interfaces;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.UserRoles.Queries.GetRoles
{
    public class GetUserRolesQueryHandler : IRequestHandler<GetUserRolesQuery, RoleVm>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _context;

        public GetUserRolesQueryHandler(IMapper mapper, IIdentityDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<RoleVm> Handle(GetUserRolesQuery request, CancellationToken cancellationToken)
        {
            return new RoleVm
            {
                Roles = await _context.UserRoles
                    .Where(ur => ur.UserId == request.UserId)
                    .Select(ur => ur.Role)
                    .ProjectTo<RoleDto>(_mapper.ConfigurationProvider)
                    .ToListAsync(cancellationToken)
            };
        }
    }
}
