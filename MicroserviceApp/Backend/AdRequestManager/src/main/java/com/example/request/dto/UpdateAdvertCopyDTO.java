package com.example.request.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateAdvertCopyDTO {

    private Long AdvertId;
    private List<DatePareDTO> reservedDateList;
}
