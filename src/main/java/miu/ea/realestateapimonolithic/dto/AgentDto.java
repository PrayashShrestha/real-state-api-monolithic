package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgentDto {
    private Long id;
    private String name;
    private String tel;
    private String location;
    private String qualification;
    private String language;
    private Double averageRating;

    private List<AgentReviewDto> reviews;
}
