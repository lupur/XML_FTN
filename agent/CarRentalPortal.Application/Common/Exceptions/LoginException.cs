using System;

namespace CarRentalPortal.Application.Common.Exceptions
{
    public class LoginException : Exception
    {
        public LoginException()
            : base("Login failed. Invalid username or password provided.")
        {
        }

        public LoginException(string message)
            : base(message)
        {
        }

        public LoginException(string message, Exception innerException)
            : base(message, innerException)
        {
        }
    }
}
