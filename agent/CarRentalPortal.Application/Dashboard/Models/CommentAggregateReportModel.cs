using System;
using System.Collections.Generic;
using System.Text;

namespace CarRentalPortal.Application.Dashboard.Models
{
    public class CommentAggregateReportModel
    {
        public int CommentCount { get; set; }
        public ICollection<CommentReportModel> Comments { get; set; }
    }
}
