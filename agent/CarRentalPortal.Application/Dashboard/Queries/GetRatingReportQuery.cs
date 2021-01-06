﻿using CarRentalPortal.Application.Dashboard.Models;
using MediatR;

namespace CarRentalPortal.Application.Dashboard.Queries
{
    public class GetRatingReportQuery : IRequest<CarReportModel>
    {
    }
}