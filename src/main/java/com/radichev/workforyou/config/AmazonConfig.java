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
                "ASIA5SN46AB7CVVUSSHL",
                "njpHo2jd4tz0qq69eyEnhUJrAIvUjJ2aNSNBSpPv",
                "FwoGZXIvYXdzEMn//////////wEaDL8Ox29Yyt7oaSW6PSLLAS4tJhFzZxcnz8xICdCKDH9dfFjhvXy4oyYoN8e3kPTLt/98dcUziFUtSCnjBStHpe/zokAej+vKP9w2xR9XBiyedSLeBRXij05aodaMf77kK4GHELvty3XGGewKK04VLqbx9nlIapMi3Ms/llXdvp0RWzquogxDuhnAU0hFdR2ryV45yK/Ivg0/wcLbC0H6iDliXfUFJM7hYvabUkA+t6vHSD4zjBisEVjVMJHxZj2eC36UgVJgnN6ktMjdv5d8NZ9SWVR7et/brQhKKJ+89PcFMi12vc1fdeo6qGhyYyVMwLDYYC+eRk1Shjmu3p2SVf+m2bprrSYNUO/Ilge5iPE="
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();

    }
}
