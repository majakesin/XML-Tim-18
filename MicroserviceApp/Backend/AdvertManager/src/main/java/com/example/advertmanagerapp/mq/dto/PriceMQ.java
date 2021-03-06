package com.example.advertmanagerapp.mq.dto;

import lombok.Data;

@Data
public class PriceMQ {

	protected Long id;
	protected String name;
	protected Float pricePerDay;
	protected Float insurancePrice;
	protected Float distanceOverflowPrice;
	protected Float discount;
	protected String owner;
	protected boolean deleted;
}
