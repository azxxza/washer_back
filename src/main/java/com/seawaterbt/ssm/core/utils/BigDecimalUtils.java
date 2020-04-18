package com.seawaterbt.ssm.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalUtils {

    /**
     * 保留两位小数
     *
     * @return BigDecimal
     */
    public static BigDecimal decimalFormat(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(number);
        return new BigDecimal(string);
    }

    public static void main(String[] args) {
        System.out.println(decimalFormat(123d));
        System.out.println(decimalFormat(123.1));
        System.out.println(decimalFormat(123.01));
        System.out.println(decimalFormat(123.33330));
    }

}
