package com.tanmingjie.tcextrade;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
                "management.health.db.enabled=false",
                "management.health.redis.enabled=false",
                "management.health.kafka.enabled=false"
        }
)
class TCexTradeApplicationTests {

    @Test
    void contextLoads() {
    }
}
