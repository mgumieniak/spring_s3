package com.mgumieniak.spring_s3;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
class Scheduler {

    private final static Logger logger = Logger.getLogger("Scheduler");

    private final static S3Client s3 = S3Client.builder().build();

    @Scheduled(fixedRateString = "30", timeUnit = TimeUnit.SECONDS)
    void getAllBuckets() {
        List<Bucket> buckets = s3.listBuckets().buckets();
        for (Bucket bucket : buckets) {
            logger.log(Level.INFO, "BUCKET: " + bucket.name());
        }
    }
}
