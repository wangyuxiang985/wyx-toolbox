package com.wyx.toolbox.datamask.config;

/**
 * @author wangyu
 */
public interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);
}
