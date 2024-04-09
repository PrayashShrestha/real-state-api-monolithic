package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.model.Agent;
import org.apache.catalina.User;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentReviewDto {
    private long id;
    private String comment;
    private int rating;
    private UserDto agent;
    private UserDto reviewer;
}
