package miu.ea.realestateapimonolithic.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ACTIVE("Active"),
    DEACTIVE("Deactive"),
    LOCKED("Locked");

    private final String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

}
