package com.example.request.service;

import com.example.request.dto.AdRequestDetailedDTO;
import com.example.request.dto.AdRequestForClientDTO;
import com.example.request.dto.CreateAdBundleRequestDTO;
import com.example.request.model.RequestBundle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientRequestService {

    RequestBundle createNewRequestBundle(CreateAdBundleRequestDTO createBundle) throws RuntimeException, Exception;
    List<AdRequestDetailedDTO> findAllBundlesByStatus(String clientEmail, String status);
    void clientPaid(Long bundleId) throws Exception, NullPointerException;
}
