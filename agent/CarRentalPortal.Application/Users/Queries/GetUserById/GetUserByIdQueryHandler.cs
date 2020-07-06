using AutoMapper;
using CarRentalPortal.Application._Common.Exceptions;
using CarRentalPortal.Application._Common.Interfaces;
using CarRentalPortal.Application.Users.Queries.GetUsers;
using CarRentalPortal.Core.Entities;
using MediatR;
using System.Threading;
using System.Threading.Tasks;

namespace CarRentalPortal.Application.Users.Queries.GetUserById
{
    public class GetUserByIdQueryHandler : IRequestHandler<GetUserByIdQuery, UserDto>
    {
        private IMapper _mapper;
        private IIdentityDbContext _context;

        public GetUserByIdQueryHandler(IMapper mapper, IIdentityDbContext context)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<UserDto> Handle(GetUserByIdQuery request, CancellationToken cancellationToken)
        {
            var user = await _context.Users.FindAsync(request.Id);
            if (user == null)
                throw new NotFoundException(nameof(User), request.Id);
            return _mapper.Map<UserDto>(user);
        }
    }
}
