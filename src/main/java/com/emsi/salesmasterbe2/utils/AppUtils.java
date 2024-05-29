package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.text.DecimalFormat;

public class AppUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Size number cannot be less than zero.");
        }
    }

    public static double formatToTwoDecimalPlaces(double number) {
        return Double.parseDouble(String.format("%.2f", number));
    }
}
