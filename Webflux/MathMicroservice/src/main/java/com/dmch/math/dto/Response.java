package com.dmch.math.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class Response {

    @EqualsAndHashCode.Exclude
    private Date date = new Date();

    private double output;

    public Response(double output) {
        this.output = output;
    }

}
