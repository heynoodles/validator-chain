package com.me.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoxin.wei
 */
public class AssertorWrapper {

    private Assertor assertor;
    private String msg;
    private Map<String, String> detail;

    public AssertorWrapper() {}

    public AssertorWrapper(Assertor acceptor, String msg) {
        this(acceptor, msg, new HashMap<String, String>());
    }

    public AssertorWrapper(Assertor acceptor, String msg, Map<String, String> detail) {
        this.assertor = acceptor;
        this.msg = msg;
        this.detail = detail;
    }

    public boolean accept() {
        return assertor.doAssert();
    }

    public Assertor getAcceptor() {
        return assertor;
    }

    public void setAcceptor(Assertor assertor) {
        this.assertor = assertor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, String> detail) {
        this.detail = detail;
    }
}
