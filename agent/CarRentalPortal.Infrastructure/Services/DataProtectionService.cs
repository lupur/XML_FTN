using CarRentalPortal.Application._Common.Interfaces;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using System;
using System.Security.Cryptography;

namespace CarRentalPortal.Infrastructure.Services
{
    public class DataProtectionService : IDataProtectionService
    {
        public string GenerateSalt(int saltSize)
        {
            byte[] salt = new byte[saltSize / 8];
            using (var rng = RandomNumberGenerator.Create())
            {
                rng.GetBytes(salt);
            }
            return Convert.ToBase64String(salt);
        }

        public string HashPassword(string password, byte[] salt, KeyDerivationPrf prf = KeyDerivationPrf.HMACSHA1, int iterationCount = 10000, int numBytesRequested = 32)
        {
            string hashedPassword = Convert.ToBase64String(KeyDerivation.Pbkdf2(
                password: password,
                salt: salt,
                prf: prf,
                iterationCount: iterationCount,
                numBytesRequested: numBytesRequested));

            return hashedPassword;
        }

        public bool ValidatePassword(string password, string hashedPassword, string salt)
        {
            return hashedPassword == HashPassword(password, Convert.FromBase64String(salt));
        }
    }
}
