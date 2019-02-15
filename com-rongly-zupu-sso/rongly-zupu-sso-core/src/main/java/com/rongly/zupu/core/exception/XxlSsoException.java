package com.rongly.zupu.core.exception;

import com.xs.rongly.framework.stater.web.exception.BizException;

/**
 * @author xuxueli 2018-04-02 21:01:41
 */
public class XxlSsoException extends BizException {

    private static final long serialVersionUID = 42L;

    public XxlSsoException(String code,String msg) {
        super(code,msg);
    }

    public XxlSsoException(String code,String msg, Throwable cause) {
        super(cause,code,msg);
    }


}
