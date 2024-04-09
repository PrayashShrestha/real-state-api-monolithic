package miu.ea.realestateapimonolithic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {
    private Long id;
    private Long originalMessageId;
    private UserDto sender;
    private String subject;
    private String content;
    private UserDto receiver;
    private LocalDateTime sentDate;
}
