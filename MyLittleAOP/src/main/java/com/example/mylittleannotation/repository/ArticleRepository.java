package com.example.mylittleannotation.repository;

import com.example.mylittleannotation.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select ar from Article ar where ar.pk in :pkSet")
    List<Article> findArticleByPkList(@Param("pkSet") Set<Long> pkSet);
}
