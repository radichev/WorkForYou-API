package com.radichev.workforyou.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 amazonS3() {
        BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
                "ASIA5SN46AB7DFN62C5R",
                "t+rFnhXfsDF9YLcf6w/Q9Lz0yj56v3OGzzSGayvf",
                "FwoGZXIvYXdzEMD//////////wEaDCjW9BwWVdb3cLFkSyLLASrfz0p0s13bi+k6S9bNhiS7aPGf8fvAoct2qGZIs38MoapzEZJSxlY7AUW5HmlYjGTbz0AmtFkIdAclSOhlXW3KBMIbDz7A4N8TrAnx5c+0jH8ZCNYIweUCTqLX5ALL8HKrtld1ofS2xza/k6huknwG4Wu/5b/dVivvebpXlopswd00YG0uy8xDAxu4oMxEtHz31ImnTQ36O3hOVAJKucu4jv5XrVNiPapGru6bBXxLHQgymJSPKZIog6oW3CucN8zD4Wf15ohASMfyKNW28vcFMi0yl3I4sIQtZoF5gpaN+cFc2QkIRPSSqsUVEiNhQCxbeqFfJW1g0kjuuFqtVxI="
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();

    }
}
