package com.ufes.blog_aulas.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException
{
    public ResourceAlreadyExistsException(String message)
    {
        super(message);
    }
}
