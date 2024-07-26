package com.github.AlissonMartin.ong.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  @Autowired
  private AmazonS3 s3Client;

  public String uploadFile(MultipartFile file) {

    try {
      File fileObj = convertMultiPartFileToFile(file);
      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      s3Client.putObject(bucketName, fileName, fileObj);
      fileObj.delete();
      return String.format("https://s3.%s.amazonaws.com/%s/%s", "sa-east-1", bucketName, fileName);
    } catch (AmazonS3Exception exception) {
      throw new RuntimeException("Failed to upload image to S3", exception);
    }

  }
  private File convertMultiPartFileToFile(MultipartFile file) {
    File convertedFile = new File(file.getOriginalFilename());
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      throw new RuntimeException("Error converting multipart file", e);
    }
    return convertedFile;
  }
}
