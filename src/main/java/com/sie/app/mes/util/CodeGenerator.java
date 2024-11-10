package com.sie.app.mes.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeGenerator {
    /**
     * 流水号
     */
    private static int sequence = 0;

    /**
     * 时间格式化
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMddHHmmssSSS");

    /**
     * 生产唯一编码方法
     * @param prefixStr
     * @return
     */
    public synchronized static String generateCode(String prefixStr,String dateFormat){

        /**
         * 获取当前时间
         */
        LocalDateTime now = LocalDateTime.now();

        /**
         * 流水号递增
         */
        int currentSeq = ++sequence;

        /**
         * 当前时间格式化
         */
        int num = Integer.parseInt(dateFormat);
        String formattedTime = now.format(formatter).substring(0,num);

        return String.format("%s%s%06d",prefixStr,formattedTime,currentSeq);
    }

    public static void main(String[] args) {
        System.out.println("第一个编码："+generateCode("M","8"));
        System.out.println("第二个编码："+generateCode("M","4"));
    }
}
