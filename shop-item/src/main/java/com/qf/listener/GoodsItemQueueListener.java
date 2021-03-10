package com.qf.listener;

import com.qf.entity.Goods;
import com.rabbitmq.client.Channel;
import freemarker.template.Configuration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class GoodsItemQueueListener {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private Configuration configuration;

    @RabbitListener(queues = "itemQueue")
    public void addGoods(Goods goods, Channel channel, Message message) throws  Exception{

        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false); // 手动ack
            }
        });

    }
}
