package miu.ea.realestateapimonolithic.common;

import lombok.Getter;

@Getter
public enum MessageStatusEnum {
    ACTIVE("Active"),
    DELETED("Deleted");

    private final String status;

    MessageStatusEnum(String status) {
        this.status = status;
    }

}
