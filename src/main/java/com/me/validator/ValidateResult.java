package com.me.validator;


import com.me.utils.Preconditions;
import com.me.utils.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoxin.wei
 */
public class ValidateResult implements Serializable {
    private String summarize;
    private Map<String, String> detail;

    public ValidateResult() {}

    public ValidateResult(String summarize, Map<String, String> detail) {
        this.summarize = summarize;
        this.detail = detail;
    }

    public boolean isValid() {
        return isValid(summarize, detail);
    }

    public static ValidateResult validResult() {
        return new ValidateResult("", Collections.EMPTY_MAP);
    }

    public static ValidateResult invalidResult(String summarize, Map<String, String> detail) {
        Preconditions.checkState(!isValid(summarize, detail));
        return new ValidateResult(summarize, detail);
    }

    public static ValidateResult invalidResult(String summarize) {
        return invalidResult(summarize, new HashMap<String, String>());
    }

    // 判断合法的validateResult
    private static boolean isValid(String summarize, Map<String, String> detail) {
        return StringUtils.isNullOrEmpty(summarize) && (detail == null || detail.isEmpty());
    }


    public String getSummarize() {
        return summarize;
    }

    public Map<String, String> getDetail() {
        return detail;
    }
}
