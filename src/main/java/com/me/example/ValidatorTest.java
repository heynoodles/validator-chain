package com.me.example;

import com.me.validator.ValidateResult;
import com.me.validator.ValidatorChain;


/**
 * @author gaoxin.wei
 */
public class ValidatorTest {

    public static void main(String[] args) {

        Biz bizData = new Biz();
        ValidateResult validateRes0 = validate0(bizData);
        if (!validateRes0.isValid()) {
            System.out.println(validateRes0.getSummarize());
        }

        ValidateResult validateRes1 = validate1(bizData);

        if (!validateRes1.isValid()) {
            System.out.println(validateRes1.getSummarize());
        }

        // 延时执行
        ValidateResult validateRes2 = validate2(bizData);
        if (!validateRes2.isValid()) {
            System.out.println(validateRes1.getSummarize());
        }
    }

    static class Biz {
        private int a = 0;
        private String b = null;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    public static ValidateResult validate2(Biz bizData) {
        return ValidatorChain.newInstance()
            .addAssert(bizData != null, "bizData不能为空")
            .addAssert(bizData.getA() > 1, "a必须大于1")
            .addAssert(() -> !bizData.getB().equals(""), "b不能为空") // 延时校验
            .validate();
    }

    public static ValidateResult validate1(Biz bizData) {
        return ValidatorChain.newInstance()
            .addAssert(bizData != null, "bizData不能为空")
            .addAssert(bizData.getA() > 1, "a必须大于1")
            .addAssert(bizData.getB() != null, "b不能为空")
            .validate();
    }

    public static ValidateResult validate0(Biz bizData) {
        if (bizData == null) {
            return ValidateResult.invalidResult("bizData不能为空");
        }

        if (bizData.getA() <= 1) {
            return ValidateResult.invalidResult("a必须大于1");
        }

        if (bizData.getB() == null) {
            return ValidateResult.invalidResult("b不能为null");
        }

        return ValidateResult.validResult();
    }
}
