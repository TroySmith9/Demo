package cn.lang.string;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yiban on 2017/6/1.
 */
public class StringSlicer {
    /** 原值 */
    private String value = null;

    /** 分隔符 */
    private String regex = null;

    public StringSlicer(String value, String regex) {
        this.value = value;
        this.regex = regex;
    }

    public List<String> getValue() {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        String[] values = value.split(regex);
        return Arrays.asList(values);
    }

    /***
     * 判断是否包含v
     * @param v
     * @return
     */
    public boolean isInclude(String v) {
        List<String> values = getValue();
        if (!CollectionUtils.isEmpty(values)) {
            for (String value : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }
}
