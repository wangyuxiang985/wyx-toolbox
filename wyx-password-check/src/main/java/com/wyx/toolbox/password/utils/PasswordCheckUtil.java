package com.wyx.toolbox.password.utils;


import com.wyx.toolbox.password.config.PasswordCheckConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 弱口令检测工具
 * @author wangyu 
 **/

@Component
public class PasswordCheckUtil {

    @Resource
    private PasswordCheckConfig passwordCheckConfig;


    private static PasswordCheckUtil passwordCheckUtil;


    @PostConstruct
    private void init() {
        passwordCheckUtil = this;
    }


    /**
     * @return 符合长度要求 返回true
     * @brief 检测密码中字符长度
     * @param[in] password            密码字符串
     */
    public static boolean checkPasswordLength(String password) {
        boolean flag = false;

        if ("".equals(passwordCheckUtil.passwordCheckConfig.MAX_LENGTH)) {
            if (password.length() >= Integer.parseInt(passwordCheckUtil.passwordCheckConfig.MIN_LENGTH)) {
                flag = true;
            }
        } else {
            if (password.length() >= Integer.parseInt(passwordCheckUtil.passwordCheckConfig.MIN_LENGTH) && password.length() <= Integer
                    .parseInt(passwordCheckUtil.passwordCheckConfig.MAX_LENGTH)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @return 包含数字 返回true
     * @brief 检测密码中是否包含数字
     * @param[in] password            密码字符串
     */
    public static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int numCount = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                numCount++;
            }
        }

        if (numCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含字母 返回true
     * @brief 检测密码中是否包含字母（不区分大小写）
     * @param[in] password            密码字符串
     */
    public static boolean checkContainCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int charCount = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                charCount++;
            }
        }

        if (charCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含小写字母 返回true
     * @brief 检测密码中是否包含小写字母
     * @param[in] password            密码字符串
     */
    public static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int charCount = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                charCount++;
            }
        }

        if (charCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含大写字母 返回true
     * @brief 检测密码中是否包含大写字母
     * @param[in] password            密码字符串
     */
    public static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int charCount = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                charCount++;
            }
        }

        if (charCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 包含特殊符号 返回true
     * @brief 检测密码中是否包含特殊符号
     * @param[in] password            密码字符串
     */
    public static boolean checkContainSpecialChar(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int special_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (passwordCheckUtil.passwordCheckConfig.SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                special_count++;
            }
        }

        if (special_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @return 含有横向连续字符串 返回true
     * @brief 键盘规则匹配器 横向连续检测
     * @param[in] password            密码字符串
     */
    public static boolean checkLateralKeyboardSite(String password) {
        String t_password = new String(password);
        //将所有输入字符转为小写
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘横向规则检测
         */
        boolean flag = false;
        int arrLen = passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR.length;
        int limit_num = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_HORIZONTAL_NUM_KEY);
        int limit_num8 = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_HORIZONTAL_NUM_KEY_LENGTH);

        for (int i = 0; i + limit_num <= limit_num8; i++) {
            String str=new String();
            String distinguishStr=new String();
            if (i + limit_num<=n){
                str = t_password.substring(i, i + limit_num);
                distinguishStr = t_password.substring(i, i + limit_num);
            }
            if (distinguishStr!=null&&!"".equalsIgnoreCase(distinguishStr)&&str!=null&&!"".equalsIgnoreCase(str)){
                for (int j = 0; j < arrLen; j++) {
                    String configStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j];
                    String revOrderStr = new StringBuffer(passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                    //检测包含字母(区分大小写)
                    if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
                        //考虑 大写键盘匹配的情况
                        String UpperStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                        if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                            flag = true;
                            return flag;
                        }
                        //考虑逆序输入情况下 连续输入
                        String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                        if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                            flag = true;
                            return flag;
                        }
                    } else {
                        if (configStr.indexOf(str) != -1) {
                            flag = true;
                            return flag;
                        }
                        //考虑逆序输入情况下 连续输入
                        if (revOrderStr.indexOf(str) != -1) {
                            flag = true;
                            return flag;
                        }
                    }
                }

            }
        }

        for (int i = 0; (limit_num8 - i) >= limit_num; i++) {
            String str = new String();
            String distinguishStr = new String();
            if (limit_num8 - i<=n){
                str = t_password.substring(limit_num8 - (i + limit_num), limit_num8 - i);
                distinguishStr = t_password.substring(limit_num8 - (i + limit_num), limit_num8 - i);
            }
            if (distinguishStr!=null&&!"".equalsIgnoreCase(distinguishStr)&&str!=null&&!"".equalsIgnoreCase(str)) {
                for (int j = 0; j < arrLen; j++) {
                    String configStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j];
                    String revOrderStr = new StringBuffer(passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                    //检测包含字母(区分大小写)
                    if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
                        //考虑 大写键盘匹配的情况
                        String UpperStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                        if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                            flag = true;
                            return flag;
                        }
                        //考虑逆序输入情况下 连续输入
                        String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                        if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                            flag = true;
                            return flag;
                        }
                    } else {
                        if (configStr.indexOf(str) != -1) {
                            flag = true;
                            return flag;
                        }
                        //考虑逆序输入情况下 连续输入
                        if (revOrderStr.indexOf(str) != -1) {
                            flag = true;
                            return flag;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有斜向连续字符串 返回true
     * @brief 键盘规则匹配器 斜向规则检测
     * @param[in] password            密码字符串
     */
    public static boolean checkKeyboardSlantSite(String password) {
        String t_password = new String(password);
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘斜线方向规则检测
         */
        boolean flag = false;
        int arrLen = passwordCheckUtil.passwordCheckConfig.KEYBOARD_SLOPE_ARR.length;
        int limit_num = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_SLOPE_NUM_KEY);

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = t_password.substring(i, i + limit_num);
            for (int j = 0; j < arrLen; j++) {
                String configStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_SLOPE_ARR[j];
                String revOrderStr = new StringBuffer(passwordCheckUtil.passwordCheckConfig.KEYBOARD_SLOPE_ARR[j]).reverse().toString();
                //检测包含字母(区分大小写)
                if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_DISTINGGUISH_CASE)) {

                    //考虑 大写键盘匹配的情况
                    String UpperStr = passwordCheckUtil.passwordCheckConfig.KEYBOARD_SLOPE_ARR[j].toUpperCase();
                    if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有a-z,z-a连续字符串 返回true
     * @brief 评估a-z,z-a这样的连续字符
     * @param[in] password            密码字符串
     */
    public static boolean checkSequentialChars(String password) {
        String t_password = new String(password);
        boolean flag = false;
        int limit_num = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_LOGIC_NUM_CHAR);
        int normal_count = 0;
        int reversed_count = 0;
        int limit_num8 = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_LOGIC_NUM_CHAR_LENGTH);

        //检测包含字母(区分大小写)
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
            t_password = t_password.toLowerCase();
        } else {
            t_password = t_password.toLowerCase();
        }
        int n = limit_num8;
        char[] pwdCharArr = t_password.toCharArray();

        for (int i = 0; i + limit_num <= n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if(i + j + 1<pwdCharArr.length){
                    if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                        normal_count++;
                        if (normal_count == limit_num - 1) {
                            return true;
                        }
                    }

                    if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                        reversed_count++;
                        if (reversed_count == limit_num - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @return 含有aaaa, 1111等连续字符串 返回true
     * @brief 评估aaaa, 1111这样的相同连续字符
     * @param[in] password            密码字符串
     */
    public static boolean checkSequentialSameChars(String password) {
        String t_password = new String(password);
        t_password = t_password.toLowerCase();
        int limit_num8 = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_LOGIC_NUM_CHAR_LENGTH);
        int n = limit_num8;
        char[] pwdCharArr = t_password.toCharArray();
        boolean flag = false;
        int limit_num = Integer.parseInt(passwordCheckUtil.passwordCheckConfig.LIMIT_NUM_SAME_CHAR);

        int count = 0;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if(i + j + 1<pwdCharArr.length){
                    if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                        count++;
                        if (count == limit_num - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 检测常用词库
     *
     * @param password
     * @return
     */
    public static boolean checkSimpleWord(String password) {
        List<String> simpleWords = Arrays.asList(passwordCheckUtil.passwordCheckConfig.SIMPLE_WORDS);
        for (String words : simpleWords) {
            if (password.toLowerCase().contains(words)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return 符合要求 返回true
     * @brief 评估密码中包含的字符类型是否符合要求
     * @param[in] password            密码字符串
     */
    public static boolean evalPassword(String password, String userName) {
        if (password == null || "".equals(password)) {
            return false;
        }
        boolean flag = false;

        /**
         * 检测长度
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_PASSWORD_LENGTH)) {
            flag = checkPasswordLength(password);
            if (!flag) {
                return false;
            }
        }

        /**
         * 包含大写、小写字母、数字、特殊符号中的3种组合
         */
        boolean containThree = checkContainThree(password);
        if (!containThree) {
            return false;
        }

        /**
         * 检测键盘横向连续
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_HORIZONTAL_KEY_SEQUENTIAL)) {
            flag = checkLateralKeyboardSite(password);
            if (flag) {
                return false;
            }
        }

        /**
         * 检测键盘斜向连续
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_SLOPE_KEY_SEQUENTIAL)) {
            flag = checkKeyboardSlantSite(password);
            if (flag) {
                return false;
            }
        }

        /**
         * 检测逻辑位置连续
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_LOGIC_SEQUENTIAL)) {
            flag = checkSequentialChars(password);
            if (flag) {
                return false;
            }
        }

        /**
         * 检测相邻字符是否相同
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_SEQUENTIAL_CHAR_SAME)) {
            flag = checkSequentialSameChars(password);
            if (flag) {
                return false;
            }
        }

        /**
         *  检测常用词库
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_SIMPLE_WORD)) {
            flag = checkSimpleWord(password);
            if (flag) {
                return false;
            }
        }
        /**
         *  检测常用词库形似变换
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_SIMILAR_WORD)) {
            flag = checkSimilarTransform(password);
            if (flag) {
                return false;
            }
        }
        /**
         *  检测公开密码库
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_PASSWORD_EXIST)) {
            flag = checkPasswordExist(password);
            if (flag) {
                return false;
            }
        }

        /**
         * 检测包含用户名
         */

        flag = checkPasswordIncludeUserName(password, userName);
        if (flag) {
            return false;
        }

        return true;
    }

    /**
     * 形似变换
     * 1、数字0、小写字母o、大写字母O
     * 2、数字1、小写字母i、大写字母I、小写字母l
     * 3、小写字母a、大写字母A、字符@
     */

    public static boolean checkSimilarTransform(String password) {
        String[] simpleWords = passwordCheckUtil.passwordCheckConfig.SIMPLE_WORDS;
        char[] pwdChars = password.toCharArray();
        boolean b = checkSimilarTransformMethod(password, pwdChars, simpleWords);
        return b;
    }


    /**
     * 检测公开密码
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordExist(String password) {
        List<String> existPassWords = Arrays.asList(passwordCheckUtil.passwordCheckConfig.PASSWORD_LIBRARY);
        for (String words : existPassWords) {
            if (password.equals(words)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测包含用户名
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordIncludeUserName(String password, String userName) {
        if (userName != null && userName.length() > 0) {
            if (password.toLowerCase().contains(userName.toLowerCase())) {
                return true;
            }

            String[] simpleWords = new String[1];
            simpleWords[0] = userName;
            char[] pwdChars = password.toCharArray();
            return checkSimilarTransformMethod(password, pwdChars, simpleWords);
        }
        return false;
    }

    public static boolean checkSimilarTransformMethod(String password, char[] pwdChars, String[] simpleWords) {
        char[] cr = new char[2];
        cr[0] = 'l';
        cr[1] = 'i';
        for (char cc : cr) {
            for (char cc2 : cr) {
                for (int i = 0; i < pwdChars.length; i++) {

                    if (pwdChars[i] == '1') {
                        // c==L
                        pwdChars[i] = cc;
                    } else if (pwdChars[i] == '0') {
                        pwdChars[i] = 'o';
                    } else if (pwdChars[i] == '!') {
                        pwdChars[i] = 'l';
                    } else if (pwdChars[i] == '@') {
                        pwdChars[i] = 'a';
                    } else if (pwdChars[i] == cc) {
                        pwdChars[i] = 'l';

                    } else if (pwdChars[i] == cc2) {
                        pwdChars[i] = 'i';

                    }

                }
                StringBuffer stringBuffer = new StringBuffer();
                for (char c : pwdChars) {
                    stringBuffer.append(c);
                }
                password = stringBuffer.toString();
                for (String simpleWord : simpleWords) {
                    if (password.toLowerCase().indexOf(simpleWord) != -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkContainThree(String password) {

        boolean numflag = false;
        boolean upperflag = false;
        boolean lowerflag = false;
        boolean specialFlag = false;
        /**
         * 检测包含数字
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_CONTAIN_DIGIT)) {
            numflag = checkContainDigit(password);
        }


        /**
         * 检测字母区分大小写
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_DISTINGGUISH_CASE)) {
            //检测包含小写字母
            if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_LOWER_CASE)) {
                lowerflag = checkContainLowerCase(password);
            }

            //检测包含大写字母
            if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_UPPER_CASE)) {
                upperflag = checkContainUpperCase(password);

            }
        }

        /**
         * 检测包含特殊符号
         */
        if ("enable".equals(passwordCheckUtil.passwordCheckConfig.CHECK_CONTAIN_SPECIAL_CHAR)) {
            specialFlag = checkContainSpecialChar(password);

        }
        if (numflag && lowerflag && upperflag && specialFlag) {
            return true;
        } else if (numflag && lowerflag && upperflag) {
            return true;
        } else if (numflag && specialFlag && upperflag) {
            return true;
        } else if (numflag && specialFlag && lowerflag) {
            return true;
        } else if (lowerflag && specialFlag && upperflag) {
            return true;
        }
        return false;
    }
}

