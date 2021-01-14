package com.gag.enterprisewxmobile.tool.common.exception;

/**
 * 业务异常
 *
 * @author zjc
 */
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}