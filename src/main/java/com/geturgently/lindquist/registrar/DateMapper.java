package com.geturgently.lindquist.registrar;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateMapper {

    public DateTimeDTO mapToDto(Date date) {
        SimpleDateFormat dateSdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeAmPmSdf = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat time24HoursSdf = new SimpleDateFormat("HH:mm:ss");

        DateTimeDTO dateTimeDTO = DateTimeDTO.builder()
                .date(dateSdf.format(date))
                .timeAmPm(timeAmPmSdf.format(date))
                .time24Hours(time24HoursSdf.format(date))
                .timestamp(date.toString())
                .build();

        return dateTimeDTO;
    }

    public Date mapToEntity(DateTimeDTO dateTimeDTO) {
        return null;
    }

}
