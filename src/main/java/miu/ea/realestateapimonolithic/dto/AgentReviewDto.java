package miu.ea.realestateapimonolithic.dto;

import lombok.*;
import miu.ea.realestateapimonolithic.model.User;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentReviewDto {
    private long id;

    private String comment;
    private int rating;

    private User user;
}
