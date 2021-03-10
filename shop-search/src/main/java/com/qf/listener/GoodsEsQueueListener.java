package com.qf.listener;

import com.qf.entity.Goods;
import com.qf.service.IGoodService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class GoodsEsQueueListener {

    @Value("${goodsQueue}")
    private String queue;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private IGoodService goodService;

    @RabbitListener(queues = "goods-es-queue")
    public void addGoods(Goods goods, Channel channel, Message message) throws  Exception{

        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("goods:"+goods);
                goodService.addGoods(goods);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false); // 手动ack
            }
        });
    }
}
