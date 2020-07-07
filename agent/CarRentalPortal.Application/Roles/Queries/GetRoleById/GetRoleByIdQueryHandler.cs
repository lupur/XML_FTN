using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Roles.Queries.GetRoleById
{
    public class GetRoleByIdQueryHandler : IRequestHandler<GetRoleByIdQuery, RoleDto>
    {
        private IMapper _mapper;
        private IIdentityDbContext _context;

        public GetRoleByIdQueryHandler(IMapper mapper, IIdentityDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<RoleDto> Handle(GetRoleByIdQuery request, CancellationToken cancellationToken)
        {
            var role = await _context.Roles.FindAsync(request.Id);
            if (role == null)
                throw new NotFoundException(nameof(Role), request.Id);

            return _mapper.Map<RoleDto>(role);
        }
    }
}
