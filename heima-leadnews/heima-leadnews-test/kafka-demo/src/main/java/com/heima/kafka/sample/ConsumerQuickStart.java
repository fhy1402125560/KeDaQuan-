package com.heima.kafka.sample;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 消费者
 */
public class ConsumerQuickStart {

    public static void main(String[] args) {
        // 1.kafka的配置信息
        Properties prop = new Properties();

        //连接地址
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.200.130:9092");
        // key和value的反序列化
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // 设置消费者组
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        //手动提交偏移量
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // 2.消费者的消费对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        // 3.订阅主题
        consumer.subscribe(Collections.singletonList("topic-first"));
        // 4.拉取消息
        /*while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key());
                System.out.println(consumerRecord.value());
                System.out.println(consumerRecord.offset());
                System.out.println(consumerRecord.partition());

                *//*try {
                    //同步提交偏移量
                    consumer.commitAsync();
                } catch (CommitFailedException e) {
                    e.printStackTrace();
                }*//*

            }*/

        // 异步提交偏移量
            /*consumer.commitSync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    if (e != null) {
                        System.out.println("记录错误的偏移量" + map + "异常信息为" + e);
                    }
                }
            });

    }*/

        try {
            while (true){
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    System.out.println(record.key());
                }
                consumer.commitAsync();
            }
        }catch (Exception e){
                e.printStackTrace();
            System.out.println("记录错误信息："+e);
        }finally {
            try {
                consumer.commitSync();
            }finally {
                consumer.close();
            }
        }
    }
}
