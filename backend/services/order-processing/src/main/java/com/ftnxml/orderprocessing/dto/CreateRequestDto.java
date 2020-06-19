package com.ftnxml.orderprocessing.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateRequestDto {
	
	private Long requestId;
	private Boolean rejected;
	private String rejectionMessage;
	private List<CreateRequestVehicleDto> vehicles = new ArrayList<>();
	
	public CreateRequestDto(Long requestId, List<Integer> vehicleIDs) {
		this.requestId = requestId;
		for(Integer vehicleId : vehicleIDs) {
			vehicles.add(new CreateRequestVehicleDto(Long.valueOf(vehicleId.toString())));
		}
	}
	
	public CreateRequestDto() {
		super();
	}
	
	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Boolean getRejected() {
		return rejected;
	}

	public void setRejected(Boolean rejected) {
		this.rejected = rejected;
	}

	public String getRejectionMessage() {
		return rejectionMessage;
	}

	public void setRejectionMessage(String rejectionMessage) {
		this.rejectionMessage = rejectionMessage;
	}

	public List<CreateRequestVehicleDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<CreateRequestVehicleDto> vehicles) {
		this.vehicles = vehicles;
	}

	public static class CreateRequestVehicleDto {
		
		private Long vehicleId;
		private Double price;
		private CreateRequestDiscountDto discount;
		
		public CreateRequestVehicleDto() {
			super();
		}
		
		public CreateRequestVehicleDto(Long vehicleId) {
			this.vehicleId = vehicleId;
		}

		public Long getVehicleId() {
			return vehicleId;
		}

		public void setVehicleId(Long vehicleId) {
			this.vehicleId = vehicleId;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}
	
		public CreateRequestDiscountDto getDiscount() {
			return discount;
		}

		public void setDiscount(CreateRequestDiscountDto discount) {
			this.discount = discount;
		}

		public static class CreateRequestDiscountDto {
			
			private Integer daysNumber;
			private Integer percentage;
			
			public CreateRequestDiscountDto() {
				super();
			}
			
			public CreateRequestDiscountDto(Integer daysNumber, Integer percentage) { 
				this.daysNumber = daysNumber;
				this.percentage = percentage;
			}

			public Integer getDaysNumber() {
				return daysNumber;
			}

			public void setDaysNumber(Integer daysNumber) {
				this.daysNumber = daysNumber;
			}

			public Integer getPercentage() {
				return percentage;
			}

			public void setPercentage(Integer percentage) {
				this.percentage = percentage;
			}
			
		}
	
	}

}
