package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;
import miu.ea.realestateapimonolithic.model.Language;
import miu.ea.realestateapimonolithic.model.Qualification;

import java.util.List;

@Getter
@Setter
public class AgentDto {
    private Long id;
    private String name;
    private String tel;
    private String location;
    private List<Qualification> qualifications;
    private List<Language> languages;
    private Double averageRating;

    private List<AgentReviewDto> reviews;
}
