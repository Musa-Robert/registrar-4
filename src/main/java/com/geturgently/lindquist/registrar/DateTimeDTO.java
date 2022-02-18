package com.geturgently.lindquist.registrar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateTimeDTO {
    private String date;
    private String timeAmPm;
    private String time24Hours;
    private String timestamp;
}