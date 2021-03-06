﻿using CarRentalPortal.API.Configurations;
using CarRentalPortal.Application._Common.Constants;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Cors.Infrastructure;
using Microsoft.AspNetCore.Http.Features;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Text;

namespace CarRentalPortal.API.Common.Extensions
{
    public static class ServiceExtensions
    {
        public static void ConfigureJwtAuthentication(this IServiceCollection services)
        {
            services.AddAuthentication(opt =>
            {
                opt.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                opt.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
            })
            .AddJwtBearer(options =>
            {
                var secretKey = Environment.GetEnvironmentVariable(AppConstants.SecretKeyString, EnvironmentVariableTarget.User) ??
                    throw new Exception("Application secret key not configured.");

                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidateLifetime = true,
                    ValidateIssuerSigningKey = true,

                    ValidIssuer = AppConstants.Issuer,
                    ValidAudience = AppConstants.Audience,
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(secretKey))
                };
            });
        }

        public static void ConfigureFormOptions(this IServiceCollection services)
        {
            services.Configure<FormOptions>(opt =>
            {
                opt.ValueLengthLimit = int.MaxValue;
                opt.MultipartBodyLengthLimit = int.MaxValue;
                opt.MemoryBufferThreshold = int.MaxValue;
            });
        }

        public static void ConfigureCors(this IServiceCollection services)
        {
            services.AddCors(options =>
            {
                options.AddPolicy("CorsPolicy",
                    builder => builder.AllowAnyOrigin()
                    .AllowAnyMethod()
                    .AllowAnyHeader());
            });
        }

        public static void AddConfigSections(this IServiceCollection services, IConfiguration configuration)
        {
            services.Configure<ResourcesConfigSection>
                (configuration.GetSection("ResourceSettings"));
        }
    }
}
