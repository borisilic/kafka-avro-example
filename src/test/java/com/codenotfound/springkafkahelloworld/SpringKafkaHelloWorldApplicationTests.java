package com.codenotfound.springkafkahelloworld;

import com.codenotfound.springkafkahelloworld.consumer.Receiver;
import com.codenotfound.springkafkahelloworld.producer.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
//@EmbeddedKafka(partitions = 1, topics = {SpringKafkaHelloWorldApplicationTests.HELLOWORLD_TOPIC})
public class SpringKafkaHelloWorldApplicationTests {

	static final String HELLOWORLD_TOPIC = "helloworld.t";

	@Autowired
	private Receiver receiver;

	@Autowired
	private Sender sender;

	@Test
	public void testReceive() throws Exception {
		sender.send("Hello spring kafka!");

		receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}
}
