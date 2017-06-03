package com.me.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gaoxin.wei
 * 对一系列串行校验进行封装，避免一大堆if条件判断
 * 注：
 * 此方法addAssert(final boolean b, String msg)不能达到延迟运算的效果
 * 建议使用ValidatorChain.addAssert(Assetor assetor, String msg)，以达到懒加载的效果
 */
public class ValidatorChain {

    private List<AssertorWrapper> acceptorWrappers = new ArrayList<AssertorWrapper>();

    public static ValidatorChain newInstance() {
        return new ValidatorChain();
    }

    public ValidatorChain addAssert(final boolean b, String msg) {
        acceptorWrappers.add(new AssertorWrapper(new Assertor() {
            @Override
            public boolean doAssert() {
                return b;
            }
        }, msg));
        return this;
    }

    public ValidatorChain addAssert(final boolean b, String msg, Map<String, String> detail) {
        acceptorWrappers.add(new AssertorWrapper(new Assertor() {
            @Override
            public boolean doAssert() {
                return b;
            }
        }, msg, detail));
        return this;
    }

    public ValidatorChain addAssert(Assertor acceptor, String msg) {
        acceptorWrappers.add(new AssertorWrapper(acceptor, msg));
        return this;
    }

    public ValidatorChain addAssert(Assertor acceptor, String msg, Map<String, String> detail) {
        acceptorWrappers.add(new AssertorWrapper(acceptor, msg, detail));
        return this;
    }

    public ValidateResult validate() {
        ValidateResult success = ValidateResult.validResult();
        if (acceptorWrappers == null || acceptorWrappers.isEmpty()) return success;

        for (AssertorWrapper acceptorWrapper : acceptorWrappers) {
            if (!acceptorWrapper.accept()) {
                return ValidateResult.invalidResult(acceptorWrapper.getMsg(), acceptorWrapper.getDetail());
            }
        }
        return success;
    }
}
