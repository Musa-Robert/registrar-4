package com.geturgently.lindquist.registrar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateMapperTest {

    private DateMapper dateMapper;

    @BeforeEach
    void init() {
        dateMapper = new DateMapper();
    }


    @Test
    public void testMapToDTO() {
        Date date = new Date();
        date.setTime(1645655917181L);
        System.out.println(date.getTime());
    }

}
