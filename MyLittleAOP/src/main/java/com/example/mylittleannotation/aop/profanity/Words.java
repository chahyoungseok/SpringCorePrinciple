package com.example.mylittleannotation.aop.profanity;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Words {
    private List<String> badWords;

    public Words(){
        badWords = new ArrayList<>(Arrays.asList(
                "욕설첫번째", "욕설두번째", "욕설세번째"
        ));
    }
}
