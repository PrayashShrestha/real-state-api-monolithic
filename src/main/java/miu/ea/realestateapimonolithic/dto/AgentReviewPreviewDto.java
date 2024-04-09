package miu.ea.realestateapimonolithic.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentReviewPreviewDto {
    private long id;
    private String comment;
    private int rating;
    private long agentId;
    private String agentEmail;
    private long reviewerId;
    private String reviewerEmail;
}
