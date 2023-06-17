package com.example.mylittleannotation.aop.profanity;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class KoreanProfanityFilter {

    private TrieNode root;
    private Set<String> allowedWords;

    public KoreanProfanityFilter() {
        root = new TrieNode();
        this.allowedWords = new HashSet<>();

        // 욕설 단어를 Trie 자료구조로 구성
        for (String word : new Words().getBadWords()) {
            addWordToTrie(word.toLowerCase());
        }

        allowedWords = new HashSet<>(Arrays.asList(
                "욕설첫번째허용", "허용욕설세번째"
        ));
    }

    private void addWordToTrie(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChild(ch);

            if (node == null) {
                node = new TrieNode();
                current.addChild(ch, node);
            }

            current = node;
        }

        current.setEndOfWord(true);
    }

    public String filterProfanity(String text) {
        StringBuilder filteredText = new StringBuilder();
        String[] words = text.split("\\s+"); // 공백을 기준으로 단어 분리

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String cleanedWord = cleanWord(word);

            if (containsProfanityWord(cleanedWord)) {
                if (allowedWords.contains(cleanedWord)) {
                    filteredText.append(word);
                } else {
                    filteredText.append(maskProfanity(cleanedWord));
                }
            } else {
                filteredText.append(word);
            }

            if (i < words.length - 1) {
                filteredText.append(" ");
            }
        }

        return filteredText.toString();
    }

    private String cleanWord(String word) {
        // 단어에서 특수 문자 제거
        return word.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
    }

    private boolean containsProfanityWord(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChild(ch);

            if (node == null) {
                break;
            }

            current = node;

            if (current.isEndOfWord()) {
                return true; // 욕설 발견
            }
        }

        return false;
    }

    private String maskProfanity(String word) {
        // 욕설을 마스킹 처리
        StringBuilder maskedWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            maskedWord.append("*");
        }

        return maskedWord.toString();
    }

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }

        public TrieNode getChild(char ch) {
            return children.get(ch);
        }

        public void addChild(char ch, TrieNode node) {
            children.put(ch, node);
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }
    }
}
