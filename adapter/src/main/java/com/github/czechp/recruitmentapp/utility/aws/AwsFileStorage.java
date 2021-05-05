package com.github.czechp.recruitmentapp.utility.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.github.czechp.recruitmentapp.utility.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service()
public class AwsFileStorage implements FileStorage {
    final private AmazonS3 amazonS3Client;

    @Autowired()
    AwsFileStorage(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        amazonS3Client.listBuckets().stream()
                .forEach(System.out::println);
    }
}
