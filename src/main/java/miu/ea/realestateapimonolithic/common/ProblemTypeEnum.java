package miu.ea.realestateapimonolithic.common;

public enum ProblemTypeEnum {
    OFFENSIVE("Offensive content"),
    IRRELEVANT("Irrelevant content"),
    SPAM("Spam (pure self-promotion)"),
    OTHER("Other");

    private String problemType;
    ProblemTypeEnum(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemType() {
        return this.problemType;
    }
}
