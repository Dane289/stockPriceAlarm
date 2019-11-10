package ro.danielstn.stockalarms.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.danielstn.stockalarms.service.stocks.StockPriceProvider;

@SpringBootTest
public class StockProviderTest {
    @Autowired
    private StockPriceProvider service;
    @Test
    void test1() {
        Double ron = service.getCurrentPriceForStock("GOLD");
    }
}
