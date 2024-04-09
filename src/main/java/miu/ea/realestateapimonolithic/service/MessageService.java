package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.dto.MessageDto;

import java.util.List;

public interface MessageService {

    void sendMessage(MessageDto messageDto);

    List<MessageDto> getSentOriginalMessages(Long userId);
    List<MessageDto> getReceiverOriginalMessages(Long userId);
    List<MessageDto> getAllMessagesByOriginalMessageId(Long messageId);
    void deleteOriginalMessage(Long messageId);
}
