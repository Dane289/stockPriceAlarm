package ro.danielstn.stockalarms.service;

import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.User;

public interface NotificationService {
    void notifyUser(User user, Alarm alarm, Double newPrice);
}
