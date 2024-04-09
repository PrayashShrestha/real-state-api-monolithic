package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.dto.MessageDto;
import miu.ea.realestateapimonolithic.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.MESSAGE_URL_PREFIX)
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
        return new ResponseEntity<>("Message sent successfully.", HttpStatus.OK);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageDto>> getSentOriginalMessages(@PathVariable Long userId) {
        return new ResponseEntity<>(messageService.getSentOriginalMessages(userId), HttpStatus.OK);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<MessageDto>> getReceiverOriginalMessages(@PathVariable Long userId) {
        return new ResponseEntity<>(messageService.getReceiverOriginalMessages(userId), HttpStatus.OK);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<List<MessageDto>> getAllMessagesByOriginalMessageId(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.getAllMessagesByOriginalMessageId(messageId), HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteOriginalMessage(messageId);
        return new ResponseEntity<>("Message deleted successfully.", HttpStatus.OK);
    }
}
