package com.example.mylittleannotation.service;

import com.example.mylittleannotation.aop.annotation.OptimizeHits;
import com.example.mylittleannotation.api.controller.dto.response.ArticleResponse;
import com.example.mylittleannotation.domain.entity.Article;
import com.example.mylittleannotation.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.example.mylittleannotation.aop.OptimizeHitsAspect.REDIS_HIT_KEY;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final RedisTemplate<String, Long> redisTemplate;

    @OptimizeHits
    public ArticleResponse read(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);

        if (article == null) {
            throw new IllegalArgumentException("조회한 articleId가 잘못되었습니다.");
        }

        return new ArticleResponse(article.getTitle(), article.getContent(), article.getHits());
    }

    public void applyHit() {
        HashOperations<String, Long, Integer> operations = redisTemplate.opsForHash();

        Set<Long> keys = operations.keys(REDIS_HIT_KEY);

        List<Article> articleList = articleRepository.findArticleByPkList(keys);
        for(Article article : articleList) {
            article.setOptimizeHit(article.getHits() + operations.get(REDIS_HIT_KEY, article.getPk()));
        }

        operations.delete(REDIS_HIT_KEY);
    }
}
