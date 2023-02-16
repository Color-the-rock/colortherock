package org.anotherclass.colortherock.domain.videoboard.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(description = "한글 색상 코드")
public enum ColorCodeKorean {
    WHITE("흰색", "white"),
    RED("빨강", "red"),
    ORANGE("주황", "orange"),
    YELLOW("노랑", "yellow"),
    GREENYELLOW("연두", "greenyellow"),
    GREEN("초록", "green"),
    SKYBLUE("하늘", "skyblue" ),
    BLUE("파랑", "blue"),
    NAVY("남색", "navy"),
    PURPLE("보라", "purple"),
    PINK("핑크", "pink"),
    BLACK("검정", "black"),
    BROWN("갈색", "brown"),
    GREY("회색", "grey");
    private final String color;
    private final String code;

    ColorCodeKorean(String color, String code) {
        this.color = color;
        this.code = code;
    }

    private static Map<String, String> colorToColorMapping;

    public static String getColor(String color){
        if(colorToColorMapping == null){
            initMapping();
        }
        return colorToColorMapping.get(color);
    }

    private static void initMapping(){
        colorToColorMapping = new HashMap<>();
        for(ColorCodeKorean cck : values()){
            colorToColorMapping.put(cck.color, cck.code);
        }
    }

}
