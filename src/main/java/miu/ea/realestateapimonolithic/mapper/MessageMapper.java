package miu.ea.realestateapimonolithic.mapper;

import miu.ea.realestateapimonolithic.common.MessageStatusEnum;
import miu.ea.realestateapimonolithic.dto.MessageDto;
import miu.ea.realestateapimonolithic.dto.UserDto;
import miu.ea.realestateapimonolithic.model.Message;
import miu.ea.realestateapimonolithic.model.User;
import org.springframework.beans.BeanUtils;

public class MessageMapper {

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);

        UserDto senderDto = new UserDto();
        BeanUtils.copyProperties(message.getSender(), senderDto);
        messageDto.setSender(senderDto);

        UserDto receiverDto = new UserDto();
        BeanUtils.copyProperties(message.getReceiver(), receiverDto);
        messageDto.setReceiver(receiverDto);
        return messageDto;
    }

    public static Message toEntity(MessageDto messageDto) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);

        User sender = User.builder()
                .id(messageDto.getSender().getId())
                .build();

        User receiver = User.builder()
                .id(messageDto.getReceiver().getId())
                .build();
        message.setSender(sender);
        message.setReceiver(receiver);

        message.setStatus(MessageStatusEnum.ACTIVE);
        return message;
    }
}
