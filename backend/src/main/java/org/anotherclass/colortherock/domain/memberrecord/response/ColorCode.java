package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(description = "레벨 색상 코드")
public enum ColorCode {
    WHITE(1, "#FFFFFF"),
    RED(2, "#FF4E36"),
    ORANGE(3, "#FFA62E"),
    YELLOW(4, "#FFCF1B"),
    GREEN(5, "#C0FA87"),
    SKYBLUE(6, "#6EE2F5"),
    INDIGO(7, "#3C5DD3"),
    PURPLE(8, "#8533FF"),
    BROWN(9, "#695F54");

    private final Integer level;
    private final String code;

    ColorCode(Integer level, String code) {
        this.level = level;
        this.code = code;
    }

    private static Map<Integer, String> levelToColorMapping;

    public static String getColor(Integer i){
        if(levelToColorMapping == null){
            initMapping();
        }
        return levelToColorMapping.get(i);
    }

    private static void initMapping(){
        levelToColorMapping = new HashMap<>();
        for(ColorCode cc : values()){
            levelToColorMapping.put(cc.level, cc.code);
        }
    }
}
