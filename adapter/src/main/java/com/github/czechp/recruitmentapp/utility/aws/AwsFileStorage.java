package com.github.czechp.recruitmentapp.utility.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.github.czechp.recruitmentapp.utility.FileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service()
public class AwsFileStorage implements FileStorage {
    final private AmazonS3 amazonS3Client;
    final private Logger logger;
    final private String bucketName;
    final private String imgPath;

    @Autowired()
    AwsFileStorage(AmazonS3 amazonS3Client,
                   @Value("${aws-file-storage.bucket-name}")String bucketName,
                   @Value("${aws-file-storage.img-path}") String imgPath
                   ) {
        this.amazonS3Client = amazonS3Client;
        this.bucketName = bucketName;
        this.imgPath = imgPath;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        createBucketIfNotExists();
    }

    private void createBucketIfNotExists() {
        if(!amazonS3Client.doesBucketExistV2(bucketName)){
            amazonS3Client.createBucket(bucketName);
            logger.info("Created bucket in Amazon AWS 3S cloud with name: {}", bucketName);
        }
    }
}
