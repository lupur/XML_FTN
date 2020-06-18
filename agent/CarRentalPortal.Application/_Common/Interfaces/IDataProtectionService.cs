using Microsoft.AspNetCore.Cryptography.KeyDerivation;

namespace CarRentalPortal.Application._Common.Interfaces
{
    public interface IDataProtectionService
    {
        public string GenerateSalt(int saltSize);
        public string HashPassword(string password, byte[] salt, KeyDerivationPrf prf = KeyDerivationPrf.HMACSHA1, int iterationCount = 10000, int numBytesRequested = 256 / 8);
        public bool ValidatePassword(string password, string hashedPassword, string salt);
    }
}
