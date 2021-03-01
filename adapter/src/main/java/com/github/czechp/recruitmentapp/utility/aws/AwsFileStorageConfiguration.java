package com.github.czechp.recruitmentapp.utility.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
class AwsFileStorageConfiguration {
    //TODO: read credentials from environment variables
    @Bean()
    AWSCredentials getAwsCredentials() {
        return new BasicAWSCredentials("AKIAIT2ZNQPKY6WZ5W2Q", "nwZHjucy+TOaevKmKLWYkhtzAe84m0qgTkf8Hi1+");
    }

    @Bean()
    public AmazonS3 getAmazonS3Client() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(getAwsCredentials()))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}
