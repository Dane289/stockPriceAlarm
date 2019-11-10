package ro.danielstn.stockalarms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.User;

@Service
public class MailNotificationService implements NotificationService {


    @Autowired
    private JavaMailSender javaMailSender;

    public void notifyUser(User user, Alarm alarm, Double newPrice) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Stocks notification ");
        msg.setText("Hi " + user.getName() + ",\n The alarm " + alarm.getName() + " was triggered for stock " +
                alarm.getStockName() + " which was " + alarm.getInitialValue() + " and now it is: " + newPrice);

        javaMailSender.send(msg);

    }
}
