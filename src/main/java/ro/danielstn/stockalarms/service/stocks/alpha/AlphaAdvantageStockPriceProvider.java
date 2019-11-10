package ro.danielstn.stockalarms.service.stocks.alpha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.danielstn.stockalarms.service.stocks.StockPriceProvider;

import java.util.LinkedHashMap;

@Service
public class AlphaAdvantageStockPriceProvider implements StockPriceProvider {

    @Value("${provider.api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    public Double getCurrentPriceForStock(String stockName) {
        final String uri = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + stockName +
                "&outputsize=compact&interval=5min&apikey=" + apiKey;


        StocksCallResponse result = restTemplate.getForObject(uri, StocksCallResponse.class);
        // Ugly parse of json incoming
        System.out.println(result);
        LinkedHashMap<String, Object> resultList = (LinkedHashMap<String, Object>) result.getDetails().get("Time Series (5min)");
        String mostRecent = resultList.keySet().iterator().next();
        LinkedHashMap<String, String> mostRecentDetails = (LinkedHashMap<String, String>) resultList.get(mostRecent);
        String value = mostRecentDetails.get("1. open");
        // parse the price
        return Double.parseDouble(value);
    }
}
