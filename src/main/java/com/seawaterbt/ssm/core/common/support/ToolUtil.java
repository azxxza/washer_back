/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seawaterbt.ssm.core.common.support;


import org.apache.commons.lang3.StringUtils;

/**
 * 高频方法集合类
 */
public class ToolUtil {

    /**
     * 校验是否含有非法字符
     * 包含非法字符时会返回非法的字符
     */
    public static String illegalChar(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String[] IIIEGAL_CHAR = {"+", "%", "^", "<", ">", "[", "]", "{", "}",
                "/", "\\", "?", "&", "(", ")"};
        for (int i = 0; i < IIIEGAL_CHAR.length; i++) {
            int index = str.indexOf(IIIEGAL_CHAR[i]);
            if (index > -1) {
                return IIIEGAL_CHAR[i];
            }
        }
        return "";
    }


}