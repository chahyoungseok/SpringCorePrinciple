package com.example.mylittleannotation.config;

import com.example.mylittleannotation.aop.KoreanProfanityAspect;
import com.example.mylittleannotation.aop.profanity.KoreanProfanityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public KoreanProfanityAspect koreanProfanityAspect(KoreanProfanityFilter koreanProfanityFilter) {
        return new KoreanProfanityAspect(koreanProfanityFilter);
    }
}
