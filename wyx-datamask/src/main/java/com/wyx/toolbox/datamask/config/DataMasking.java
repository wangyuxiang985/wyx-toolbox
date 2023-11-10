package com.wyx.toolbox.datamask.config;

import java.lang.annotation.*;

/**
 * @author wangyu
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {

    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;
}
