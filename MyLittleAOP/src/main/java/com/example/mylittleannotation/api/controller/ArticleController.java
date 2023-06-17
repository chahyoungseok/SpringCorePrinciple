package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.dto.response.ArticleResponse;
import com.example.mylittleannotation.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public ResponseEntity<ArticleResponse> read(@RequestParam Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.read(articleId));
    }

    @GetMapping("/hit_apply")
    public void apply() {
        articleService.applyHit();
    }
}
