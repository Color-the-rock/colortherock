package org.anotherclass.colortherock.domain.videoboard.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(description = "한글 색상 코드")
public enum ColorCodeKorean {
    WHITE("하양", "#FFFFFF");

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
