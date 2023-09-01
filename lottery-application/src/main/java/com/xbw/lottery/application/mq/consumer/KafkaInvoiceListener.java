package com.xbw.lottery.application.mq.consumer;

import cn.hutool.core.lang.Assert;
import com.google.gson.Gson;
import com.xbw.lottery.application.mq.producer.KafkaProducer;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.activity.model.vo.InvoiceVO;
import com.xbw.lottery.domain.award.model.req.GoodsReq;
import com.xbw.lottery.domain.award.model.res.DistributionRes;
import com.xbw.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.xbw.lottery.domain.award.service.goods.IDistributionGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 中奖发货单监听消息
 */
@Component
@Slf4j
public class KafkaInvoiceListener {

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    Gson gson = new Gson();

    @KafkaListener(topics = KafkaProducer.TOPIC_INVOICE, groupId = KafkaProducer.INVOICE_GROUP)
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());

        // 1.判断消息是否存在
        if (!message.isPresent()) {
            return;
        }

        // 2.处理mq消息
        try {
            // 2.1 反序列化
            InvoiceVO invoiceVO = gson.fromJson((String) message.get(), InvoiceVO.class);

            // 2.2 获取对应的发送奖品工厂，执行发货
            IDistributionGoods distributionGoodsService
                    = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            GoodsReq goodsReq = new GoodsReq(invoiceVO.getuId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(),
                    invoiceVO.getAwardName(), invoiceVO.getAwardContent());
            DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);

            Assert.isTrue(Constants.AwardState.SUCCESS.getCode().equals(distributionRes.getCode()), distributionRes.getInfo());

            // 2.3 记录日志
            log.info("消费MQ消息，完成 topic：{} uId：{} 发奖结果：{}", topic, invoiceVO.getuId(), gson.toJson(distributionRes));

            // 2.4 ack
            ack.acknowledge();

        } catch (Exception e) {
            // 发奖环节失败，消息重试。所有的环节，发货、更新库，都需要保证幂等。
            log.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }
    }
}
