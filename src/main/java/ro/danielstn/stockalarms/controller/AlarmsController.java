package ro.danielstn.stockalarms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.User;
import ro.danielstn.stockalarms.service.AlarmsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alarms")
public class AlarmsController {

    @Autowired
    private AlarmsService alarmsService;

    @GetMapping
    public List<AlarmsDTO> getAlarms() {
        User user = new User().setId(1);// TODO change user to the one authenticated
        List<Alarm> alarmsForUser = alarmsService.findAlarmsForUser(user);
        return alarmsForUser.stream().map(alarm -> new AlarmsDTO()
                    .setActive(alarm.isActive())
                    .setId(alarm.getId())
                    .setStockName(alarm.getStockName())
                    .setInitialValue(alarm.getInitialValue())
                    .setVariance(alarm.getVariance()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public List<AlarmsDTO> saveAlarms(AlarmsDTO alarmsDTO) {
        User user = new User();// TODO change user to the one authenticated
        alarmsService.saveAlarm(
                new Alarm()
                        .setUser(user)
                        .setName(alarmsDTO.getName())
                        .setStockName(alarmsDTO.getStockName())
                        .setVariance(alarmsDTO.getVariance())
        );
        return getAlarms();
    }




}
