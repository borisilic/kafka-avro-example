package com.codenotfound.springkafkahelloworld;

import com.codenotfound.springkafkahelloworld.consumer.Receiver;
import com.codenotfound.springkafkahelloworld.producer.Sender;
import example.avro.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource({
        "classpath:application.yml",
        "classpath:override-test-properties.yml"
})
public class SpringKafkaHelloWorldApplicationTests {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    @Test
    public void testReceive() throws Exception {
        User user = User.newBuilder().setName("Boris Ilic").setFavoriteColor("Green").setFavoriteNumber(null).build();
        sender.send(user);

        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
