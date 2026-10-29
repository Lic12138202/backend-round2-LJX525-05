package com.studio.data.exception;

/** 业务异常，携带 HTTP 状态码 */
public class BizException extends RuntimeException {

    private final int status;

    public BizException(int status, String message) {
        super(message);
        this.status = status;
    }

    public static BizException badRequest(String message) {
        return new BizException(400, message);
    }

    public static BizException notFound(String message) {
        return new BizException(404, message);
    }

    public int getStatus() {
        return status;
    }
}
