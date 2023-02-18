package com.analytics;

import com.analytics.model.Bill;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.function.Function;

@SpringBootApplication
public class AnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsServiceApplication.class, args);
	}

	@Bean
	public Function<KStream<String, Bill>, KStream<String, Long>> kStreamFunction(){

		return (input) -> {
			System.out.println(input.toString());
			return input
					.filter((k, v)->v.getProductItems()!=null)
					.map((k,v)->new KeyValue<>(v.getCustomer().getName(), 0L))
					.groupBy((k,v)->k, Grouped.with(Serdes.String(), Serdes.Long()))
					.windowedBy(TimeWindows.of(Duration.ofMillis(5000)))
					.count(Materialized.as("orders-analytics-store"))
					.toStream()
					.map((k,v)->new KeyValue<>("=>" + k.window().startTime() + k.window().endTime() + k.key(),v));
		};
	}

}
