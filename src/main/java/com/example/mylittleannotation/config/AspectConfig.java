package com.example.mylittleannotation.config;

import com.example.mylittleannotation.aop.AuthorizationAspect;
import com.example.mylittleannotation.aop.KoreanProfanityAspect;
import com.example.mylittleannotation.aop.profanity.KoreanProfanityFilter;
import com.example.mylittleannotation.api.controller.aes.AES;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public KoreanProfanityAspect koreanProfanityAspect(KoreanProfanityFilter koreanProfanityFilter) {
        return new KoreanProfanityAspect(koreanProfanityFilter);
    }

    @Bean
    public AuthorizationAspect authorizationAspect(AES aes) {
        return new AuthorizationAspect(aes);
    }
}
