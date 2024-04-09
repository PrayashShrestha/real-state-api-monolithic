package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgentDto {
    private String name;
    private String location;
    private String qualification;
    private String language;
    private Integer rating;

    private List<AgentReviewDto> reviews;
}
