package com.xbw.lottery.application.mq.producer;

import com.google.gson.Gson;
import com.xbw.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.xbw.lottery.domain.activity.model.vo.InvoiceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * MQ 消息发送服务
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    Gson gson = new Gson();

    // 中奖发货单 topic
    public static final String TOPIC_INVOICE = "lottery-invoice";

    // consumer group
    public static final String CONSUMER_GROUP = "lottery";


    // 活动领取记录 topic
    public static final String TOPIC_ACTIVITY_PARTAKE = "lottery_activity_partake";



    /**
     * 发送中奖物品发货单消息
     *
     * @param invoice 发货单
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoice) {
        String obj2json = gson.toJson(invoice);

        log.info("发送MQ消息(中奖发货单) topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoice.getuId(), obj2json);

        return kafkaTemplate.send(TOPIC_INVOICE, obj2json);
    }

    /**
     * 发送领取活动记录MQ
     *
     * @param activityPartakeRecord 领取活动记录
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryActivityPartakeRecord(ActivityPartakeRecordVO activityPartakeRecord) {
        String obj2json = gson.toJson(activityPartakeRecord);
        log.info("发送MQ消息(领取活动记录) topic：{} bizId：{} message：{}", TOPIC_ACTIVITY_PARTAKE, activityPartakeRecord.getuId(), obj2json);
        return kafkaTemplate.send(TOPIC_ACTIVITY_PARTAKE, obj2json);
    }

}
