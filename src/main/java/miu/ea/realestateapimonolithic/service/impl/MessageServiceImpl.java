package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.MessageStatusEnum;
import miu.ea.realestateapimonolithic.dto.MessageDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.mapper.MessageMapper;
import miu.ea.realestateapimonolithic.model.Message;
import miu.ea.realestateapimonolithic.model.Property;
import miu.ea.realestateapimonolithic.model.User;
import miu.ea.realestateapimonolithic.repository.MessageRepository;
import miu.ea.realestateapimonolithic.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void sendMessage(MessageDto messageDto) {
        messageRepository.save(MessageMapper.toEntity(messageDto));
    }

    @Override
    public List<MessageDto> getSentOriginalMessages(Long userId) {
        List<Message> messages =
                messageRepository.findByOriginalMessageIdIsNullAndSenderIdAndStatusOrderByIdDesc(userId
                        , MessageStatusEnum.ACTIVE);
        return messages.stream().map(message -> MessageMapper.toDto(message)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getReceiverOriginalMessages(Long userId) {
        List<Message> messages =
                messageRepository.findByOriginalMessageIdIsNullAndReceiverIdAndStatusOrderByIdDesc(userId
                        , MessageStatusEnum.ACTIVE);
        return messages.stream().map(message -> MessageMapper.toDto(message)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getAllMessagesByOriginalMessageId(Long messageId) {
        List<Message> messages = messageRepository.findByIdOrOriginalMessageIdAndStatusOrderById(messageId, messageId
                , MessageStatusEnum.ACTIVE);
        return messages.stream().map(message -> MessageMapper.toDto(message)).collect(Collectors.toList());
    }

    @Override
    public void deleteOriginalMessage(Long messageId) {
        Message existingMessage = messageRepository.findById(messageId).orElseThrow(
                () -> {
                    return new NotFoundException("Message not found, id=" + messageId);
                }
        );
        existingMessage.setStatus(MessageStatusEnum.DELETED);
        messageRepository.save(existingMessage);
    }
}
