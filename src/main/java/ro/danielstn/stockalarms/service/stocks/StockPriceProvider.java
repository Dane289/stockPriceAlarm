package ro.danielstn.stockalarms.service.stocks;

public interface StockPriceProvider {

    Double getCurrentPriceForStock(String stockName);
}
