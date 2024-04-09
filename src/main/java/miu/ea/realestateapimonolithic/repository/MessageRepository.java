package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.common.MessageStatusEnum;
import miu.ea.realestateapimonolithic.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByOriginalMessageIdIsNullAndSenderIdAndStatusOrderByIdDesc(Long senderId
            , MessageStatusEnum status);
    List<Message> findByOriginalMessageIdIsNullAndReceiverIdAndStatusOrderByIdDesc(Long receiverId
            , MessageStatusEnum status);
    List<Message> findByIdOrOriginalMessageIdAndStatusOrderById(Long messageId1, Long messageId2
            , MessageStatusEnum status);
}
