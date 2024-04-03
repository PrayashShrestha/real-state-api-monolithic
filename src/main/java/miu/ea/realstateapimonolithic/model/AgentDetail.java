package miu.ea.realstateapimonolithic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentDetail {
    @Id
    private long id;

    private String qualification;
    private String language;

    @OneToMany
    @JoinColumn(name="agent_id")
    private List<AgentReview> reviews = new ArrayList<>();
}
