﻿using System;
using System.Collections.Generic;
using System.Text;

namespace CarRentalPortal.Core.Entities
{
    public class User
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string Salt { get; set; }
    }
}
