package com.radichev.workforyou.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 amazonS3() {
        BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
                "ASIA5SN46AB7H3PMKNH2",
                "1twFZZgdpn+o+aaU1z467qDQv0giHcPu234VCNs6",
                "FwoGZXIvYXdzENT//////////wEaDDdeY1KUmSGzQ/mpryLLAdwm+owRbvdDNIAKarsVzIcrHNf6HVDkTcfKAStAOogNb7nUze0YcNVhDPZjQ2ygS1LoTZNflgbrmN1xjl2DuDuR8VBcs6QWpTfO01tnfA1ivUQO/YwxU4+iZ1FojIydrNu3+VXkM5c05XGHv2u386Y57cBny8UnGHtP8zuN+T1oJkI1lUTN6w42QfeCb9ayqMa2Yn94BpCLZU3wlJ/1DAW2IsOGOJtkUYEsmwH+sbGkkOnnSuBqK16A2j8PKlr7IcWp5lox0OOmS86xKNPy9vcFMi0K9Qmu30g+SZS2MgOkdpig1rPb6V+fPWlhFP44zx98kru4CSP2GHwDGAbQECA="
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();

    }
}
