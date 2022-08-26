package com.techelevator.services.uploads;

import org.springframework.web.multipart.MultipartFile;

public interface UploadProvider
{
    public String uploadFile ( MultipartFile file, String fileName ) throws Throwable;
}

