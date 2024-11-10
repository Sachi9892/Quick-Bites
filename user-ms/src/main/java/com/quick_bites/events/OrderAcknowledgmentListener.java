package com.quick_bites.events;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class OrderAcknowledgmentListener {

    @Bean
    public Consumer<OrderAcknowledgment> orderAcknowledged() {
        return acknowledgment -> log.info("Acknowledgment received: {} " , acknowledgment);
    }

}
