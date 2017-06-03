package com.me.example;

import com.me.validator.ValidateResult;
import com.me.validator.ValidatorChain;


/**
 * @author gaoxin.wei
 */
public class ValidatorTest {

    public static void main(String[] args) {

        int a = 0;
        String b = null;

        ValidateResult validateRes = ValidatorChain.newInstance()
            .addAssert(a > 1, "a必须大于1")
            .addAssert(b != null, "b不能为空")
            .validate();

        if (!validateRes.isValid()) {
            System.out.println(validateRes.getSummarize());
        }

        // 延时执行
        ValidateResult validateRes1 = ValidatorChain.newInstance()
            .addAssert(a > 1, "a必须大于1")
            .addAssert(() -> !b.equals(""), "b不能为空")
            .validate();

        if (!validateRes.isValid()) {
            System.out.println(validateRes1.getSummarize());
        }
    }
}
