package com.wyx.toolbox.datamask.vo;

import com.wyx.toolbox.datamask.config.DataMasking;
import com.wyx.toolbox.datamask.config.DataMaskingFunc;
import lombok.Data;

@Data
public class DemoVo {

    private Long id;

    @DataMasking(maskFunc = DataMaskingFunc.USERNAME)
    private String userName;

    @DataMasking(maskFunc = DataMaskingFunc.PHONE)
    private String phone;

    @DataMasking(maskFunc = DataMaskingFunc.EMAIL)
    private String email;

    private String address;
}
