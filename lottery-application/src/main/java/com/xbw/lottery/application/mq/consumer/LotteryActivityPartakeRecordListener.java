package com.xbw.lottery.application.mq.consumer;

import com.google.gson.Gson;
import com.xbw.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.xbw.lottery.domain.activity.service.partake.IActivityPartake;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

import static com.xbw.lottery.application.mq.producer.KafkaProducer.CONSUMER_GROUP;
import static com.xbw.lottery.application.mq.producer.KafkaProducer.TOPIC_ACTIVITY_PARTAKE;

/**
 * 抽奖活动领取记录监听消息
 */
@Component
@Slf4j
public class LotteryActivityPartakeRecordListener {


    @Resource
    private IActivityPartake activityPartake;

    Gson gson = new Gson();

    @KafkaListener(topics = TOPIC_ACTIVITY_PARTAKE, groupId = CONSUMER_GROUP)
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());

        // 1. 判断消息是否存在
        if (!message.isPresent()) {
            return;
        }

        // 2.处理mq消息
        try {
            // 2. 反序列化
            ActivityPartakeRecordVO activityPartakeRecordVO = gson.fromJson((String) message.get(), ActivityPartakeRecordVO.class);
            log.info("消费MQ消息，异步更新数据库库存 message：{}", message.get());

            // 3. 更新数据库库存【实际场景业务体量较大，可能也会由于MQ消费引起并发，对数据库产生压力，所以如果并发量较大，可以把库存记录缓存中，并使用定时任务进行处理缓存和数据库库存同步，减少对数据库的操作次数】
            activityPartake.updateActivityStock(activityPartakeRecordVO);
            ack.acknowledge();
        } catch (Exception e) {
            // 更新数据库库存失败，消息重试。所有的环节，发货、更新库，都需要保证幂等。
            log.error("更新数据库库存失败，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }

    }

}
