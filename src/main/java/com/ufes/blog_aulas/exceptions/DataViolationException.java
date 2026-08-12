package com.ufes.blog_aulas.exceptions;

public class DataViolationException extends RuntimeException
{
    public DataViolationException(String message)
    {
        super(message);
    }
}
