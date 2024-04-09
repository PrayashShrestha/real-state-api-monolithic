package miu.ea.realestateapimonolithic.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentReviewPreviewDto {
    private Long id;
    private String comment;
    private int rating;
    private Long agentId;
    private String agentEmail;
    private Long reviewerId;
    private String reviewerEmail;
}