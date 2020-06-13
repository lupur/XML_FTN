﻿using MediatR;

namespace CarRentalPortal.Application.Auth.Commands
{
    public class LoginCommand : IRequest<string>
    {
        public string Email { get; set; }
        public string Password { get; set; }
    }
}
