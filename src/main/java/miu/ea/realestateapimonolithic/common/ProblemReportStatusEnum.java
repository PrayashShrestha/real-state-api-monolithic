package miu.ea.realestateapimonolithic.common;

import lombok.Getter;

@Getter
public enum ProblemReportStatusEnum {
    NEW("New"),
    PROCESSING("Processing"),
    PENDING("Pending"),
    CLOSED("Closed");

    private final String status;

    ProblemReportStatusEnum(String status) {
        this.status = status;
    }

}
