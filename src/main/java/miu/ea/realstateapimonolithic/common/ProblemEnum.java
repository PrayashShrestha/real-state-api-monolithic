package miu.ea.realstateapimonolithic.common;

public enum ProblemEnum {
    OFFENSIVE("Offensive content"),
    IRRELEVANT("Irrelevant content"),
    SPAM("Spam (pure self-promotion)"),
    OTHER("Other");

    private String problem;
    ProblemEnum(String problem) {
        this.problem = problem;
    }

    public String getProblem() {
        return this.problem;
    }
}
