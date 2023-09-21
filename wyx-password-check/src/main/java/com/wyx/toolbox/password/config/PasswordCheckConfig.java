package com.wyx.toolbox.password.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 弱口令检测配置类
 * @author wangyu
 **/

@Component
public class PasswordCheckConfig {
    /**
     * 是否检测密码口令长度
     */
    @Value("${PasswordCheck.CHECK_PASSWORD_LENGTH}")
    public   String CHECK_PASSWORD_LENGTH;
    /**
     * 密码最小长度，默认为8
     */
    @Value("${PasswordCheck.MIN_LENGTH}")
    public   String MIN_LENGTH;
    /**
     * 密码最大长度，默认为20
     */
    @Value("${PasswordCheck.MAX_LENGTH}")
    public   String MAX_LENGTH;

    /**
     * 是否包含数字
     */
    @Value("${PasswordCheck.CHECK_CONTAIN_DIGIT}")
    public   String CHECK_CONTAIN_DIGIT;

    /**
     * 是否包含字母
     */
    @Value("${PasswordCheck.CHECK_CONTAIN_CASE}")
    public   String CHECK_CONTAIN_CASE;

    /**
     * 是否区分大小写
     */
    @Value("${PasswordCheck.CHECK_DISTINGGUISH_CASE}")
    public   String CHECK_DISTINGGUISH_CASE;


    /**
     * 是否包含小写字母
     */
    @Value("${PasswordCheck.CHECK_LOWER_CASE}")
    public   String CHECK_LOWER_CASE;


    /**
     * 是否包含大写字母
     */
    @Value("${PasswordCheck.CHECK_UPPER_CASE}")
    public   String CHECK_UPPER_CASE;


    /**
     * 是否包含特殊符号
     */
    @Value("${PasswordCheck.CHECK_CONTAIN_SPECIAL_CHAR}")
    public   String CHECK_CONTAIN_SPECIAL_CHAR;
    /**
     * 特殊符号集合
     */
    public   String SPECIAL_CHAR="!\\#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";


    /**
     * 是否检测键盘按键横向连续
     */
    @Value("${PasswordCheck.CHECK_HORIZONTAL_KEY_SEQUENTIAL}")
    public   String CHECK_HORIZONTAL_KEY_SEQUENTIAL;
    /**
     * 键盘物理位置横向不允许最小的连续个数
     */
    @Value("${PasswordCheck.LIMIT_HORIZONTAL_NUM_KEY}")
    public   String LIMIT_HORIZONTAL_NUM_KEY;

    /**
     * 键盘物理位置横向不允许最小的连续个数(8位内)
     */
    @Value("${PasswordCheck.LIMIT_HORIZONTAL_NUM_KEY_LENGTH}")
    public   String LIMIT_HORIZONTAL_NUM_KEY_LENGTH;


    /**
     * 是否检测键盘按键斜向连续
     */
    @Value("${PasswordCheck.CHECK_SLOPE_KEY_SEQUENTIAL}")
    public   String CHECK_SLOPE_KEY_SEQUENTIAL;
    /**
     * 键盘物理位置斜向不允许最小的连续个数
     */
    @Value("${PasswordCheck.LIMIT_SLOPE_NUM_KEY}")
    public   String LIMIT_SLOPE_NUM_KEY;


    /**
     * 是否检测逻辑位置连续
     */
    @Value("${PasswordCheck.CHECK_LOGIC_SEQUENTIAL}")
    public   String CHECK_LOGIC_SEQUENTIAL;
    /**
     * 密码口令中字符在逻辑位置上不允许最小的连续个数
     */
    @Value("${PasswordCheck.LIMIT_LOGIC_NUM_CHAR}")
    public   String LIMIT_LOGIC_NUM_CHAR;

    @Value("${PasswordCheck.LIMIT_LOGIC_NUM_CHAR_LENGTH}")
    public   String LIMIT_LOGIC_NUM_CHAR_LENGTH;


    /**
     * 是否检测连续字符相同
     */
    @Value("${PasswordCheck.CHECK_SEQUENTIAL_CHAR_SAME}")
    public   String CHECK_SEQUENTIAL_CHAR_SAME;
    /**
     * 密码口令中相同字符不允许最小的连续个数
     */
    @Value("${PasswordCheck.LIMIT_NUM_SAME_CHAR}")
    public   String LIMIT_NUM_SAME_CHAR;


    /**
     * 键盘横向方向规则
     */
    @Value("${PasswordCheck.KEYBOARD_HORIZONTAL_ARR}")
    public   String[] KEYBOARD_HORIZONTAL_ARR;
    /**
     * 键盘斜线方向规则
     */
    @Value("${PasswordCheck.KEYBOARD_SLOPE_ARR}")
    public   String[] KEYBOARD_SLOPE_ARR;


    /**
     * 是否检测常用词库
     */
    @Value("${PasswordCheck.CHECK_SIMPLE_WORD}")
    public   String CHECK_SIMPLE_WORD;


    /**
     * 是否检测常用词库形似变换
     */
    @Value("${PasswordCheck.CHECK_SIMILAR_WORD}")
    public   String CHECK_SIMILAR_WORD;

    /**
     * 是否检测已公开的密码
     */
    @Value("${PasswordCheck.CHECK_PASSWORD_EXIST}")
    public   String CHECK_PASSWORD_EXIST;


    /**
     * 常用词库
     */
    @Value("${PasswordCheck.SIMPLE_WORDS}")
    public   String[] SIMPLE_WORDS;


    /**
     * 公开密码库
     */
    @Value("${PasswordCheck.PASSWORD_LIBRARY}")
    public   String[] PASSWORD_LIBRARY;

}