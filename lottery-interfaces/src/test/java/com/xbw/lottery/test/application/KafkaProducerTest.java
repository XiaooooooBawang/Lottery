package com.xbw.lottery.test.application;

import com.xbw.lottery.application.mq.producer.KafkaProducer;
import com.xbw.lottery.common.Constants;
import com.xbw.lottery.domain.activity.model.vo.InvoiceVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Kafka 消息测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KafkaProducerTest {

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        InvoiceVO invoice = new InvoiceVO();
        invoice.setuId("sdf");
        invoice.setOrderId(1444540456057864192L);
        invoice.setAwardId(3L);
        invoice.setAwardType(Constants.AwardType.DESC.getCode());
        invoice.setAwardName("Code");
        invoice.setAwardContent("苹果电脑");
        invoice.setShippingAddress(null);
        invoice.setExtInfo(null);

        kafkaProducer.sendLotteryInvoice(invoice);

        while (true){
            Thread.sleep(10000);
        }
    }
}
