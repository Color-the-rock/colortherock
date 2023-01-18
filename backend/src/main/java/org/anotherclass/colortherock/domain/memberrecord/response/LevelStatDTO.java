package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LevelStatDTO {
    int level;
    long total;
    long success;

    public LevelStatDTO(int level) {
        this.level = level;
    }

    public void totalIncrement() {
        this.total += 1;
    }

    public void successIncrement() {
        this.success += 1;
    }
}
