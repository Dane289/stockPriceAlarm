package ro.danielstn.stockalarms.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.AlarmRepository;
import ro.danielstn.stockalarms.service.NotificationService;
import ro.danielstn.stockalarms.service.stocks.StockPriceProvider;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StockPriceAlarmService {

    @Autowired
    private StockPriceProvider priceProvider;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    @Scheduled(fixedRateString = "${stocks.fetch.interval.in.milis}")
    public void runJob()  {
        List<Alarm> activeAlarms = alarmRepository.findByActive(true);
        Set<String> uniqueStockNames = activeAlarms.stream().map(a -> a.getStockName()).collect(Collectors.toSet());

        final Map<String, Double> stockToPrice = new HashMap<>();
        uniqueStockNames.forEach(stock -> stockToPrice.put(stock, priceProvider.getCurrentPriceForStock(stock)));

        activeAlarms.forEach(alarm -> triggerIfneeded(alarm, stockToPrice.get(alarm.getStockName())));
    }

    void triggerIfneeded(Alarm alarm, Double updatedPrice) {
            double percent = 1 + alarm.getVariance() / 100;
        // case increase
        if(alarm.getVariance() > 0) {
            if (updatedPrice >= alarm.getInitialValue() * percent) {
                alarmTriggered(alarm, updatedPrice);
            }
        } else {
            // case decrease
            if (updatedPrice <= alarm.getInitialValue() * percent) {
                alarmTriggered(alarm, updatedPrice);
            }
        }
    }

    private void alarmTriggered(Alarm alarm, Double updatedPrice) {
        notificationService.notifyUser(alarm.getUser(), alarm, updatedPrice);
        alarm.setActive(false);
        alarmRepository.save(alarm);
    }
}
