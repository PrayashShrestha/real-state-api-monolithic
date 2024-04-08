package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentSearchRequest {
    private String location;
    private String qualification;
    private String language;
    private int rating;
}
