package ro.danielstn.stockalarms.service;

import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.AlarmRepository;
import ro.danielstn.stockalarms.datasource.User;
import ro.danielstn.stockalarms.service.stocks.StockPriceProvider;

import java.util.List;

@Service
public class AlarmsService {
    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private StockPriceProvider stockPriceProvider;

    public List<Alarm> findAlarmsForUser(User user) {
        return alarmRepository.findByUser(user);
    }

    public void saveAlarm(Alarm alarm) {
        try {
            Double currentPriceForStock = stockPriceProvider.getCurrentPriceForStock(alarm.getStockName());
            alarmRepository.save(alarm.setActive(true).setInitialValue(currentPriceForStock));
        } catch (RuntimeException ex) {
            // something went wrong, the stock does not exist => no save
            ex.printStackTrace();
        }
    }
}