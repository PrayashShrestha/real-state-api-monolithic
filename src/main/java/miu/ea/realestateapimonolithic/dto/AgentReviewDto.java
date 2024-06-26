package miu.ea.realestateapimonolithic.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgentReviewDto {
    private long id;
    private String comment;
    private int rating;
    private UserDto agent;
    private UserDto reviewer;
}