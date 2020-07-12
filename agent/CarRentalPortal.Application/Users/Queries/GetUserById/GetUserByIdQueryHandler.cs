using AutoMapper;
using AutoMapper.QueryableExtensions;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Roles.Queries;
using CarRentalPortal.Application.Users.Queries.GetUsers;
using CarRentalPortal.Core.Entities;
using MediatR;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Queries.GetUserById
{
    public class GetUserByIdQueryHandler : IRequestHandler<GetUserByIdQuery, UserDto>
    {
        private readonly IMapper _mapper;
        private readonly IIdentityDbContext _context;

        public GetUserByIdQueryHandler(IMapper mapper, IIdentityDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<UserDto> Handle(GetUserByIdQuery request, CancellationToken cancellationToken)
        {
            var user = _mapper.Map<UserDto>(await _context.Users.FindAsync(request.Id));
            if (user == null)
                throw new NotFoundException(nameof(User), request.Id);

            var userRoles = await _context.UserRoles
                .Where(ur => ur.UserId == user.Id).Select(ur => ur.RoleId).ToListAsync(cancellationToken);
            user.Roles = await _context.Roles
                .Where(r => userRoles.Contains(r.Id)).ProjectTo<RoleDto>(_mapper.ConfigurationProvider).ToListAsync(cancellationToken);
            return user;
        }
    }
}
