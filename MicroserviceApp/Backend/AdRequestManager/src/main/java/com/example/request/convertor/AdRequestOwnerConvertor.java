package com.example.request.convertor;

import java.util.HashSet;
import java.util.Set;


import com.example.request.dto.AdRequestDTO;
import com.example.request.dto.AdRequestForOwnerDTO;
import com.example.request.model.RequestBundle;

public abstract class AdRequestOwnerConvertor {

	public static AdRequestForOwnerDTO fromBundleToAdRequestForOwner(RequestBundle bundle) throws NullPointerException {
		
		AdRequestForOwnerDTO dto = new AdRequestForOwnerDTO();
		dto.setPriceWithDiscount(bundle.getPriceWithDiscount());
		dto.setRequestBundleId(bundle.getRequestBundleId());
		dto.setRequestingUserEmail(bundle.getOwnerEmail());
		dto.setStatus(bundle.getAdvertState().toString());

		Set<AdRequestDTO> requests = new HashSet<AdRequestDTO>();
		if(bundle.getRequests().size() != 0) {
			 bundle.getRequests().forEach(request -> {
			 	AdRequestDTO r = new AdRequestDTO();
			 	r.setAdvertId(request.getRequestId());
			 	r.setEndDate(request.getStartReservationDate());
			 	r.setStartDate(request.getEndReservationDate());

			 	requests.add(r);
			 });
		}

		dto.setRequests(requests);

		return dto;
	}
}