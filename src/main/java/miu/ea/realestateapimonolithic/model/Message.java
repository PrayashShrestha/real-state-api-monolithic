package miu.ea.realestateapimonolithic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import miu.ea.realestateapimonolithic.common.MessageStatusEnum;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originalMessageId;

    @ManyToOne
    private User sender;

    private String subject;

    @Column(length = 2000)
    private String content;

    @ManyToOne
    private User receiver;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentDate;

    @Enumerated(EnumType.STRING)
    private MessageStatusEnum status;
}
