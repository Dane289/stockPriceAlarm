package ro.danielstn.stockalarms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.danielstn.stockalarms.datasource.Alarm;
import ro.danielstn.stockalarms.datasource.User;
import ro.danielstn.stockalarms.service.AlarmsService;
import ro.danielstn.stockalarms.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alarms")
public class AlarmsController {

    @Autowired
    private AlarmsService alarmsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AlarmsDTO> getAlarms(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
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
    public List<AlarmsDTO> saveAlarms(Principal principal, AlarmsDTO alarmsDTO) {
        User user = userService.getUserByUsername(principal.getName());
        alarmsService.saveAlarm(
                new Alarm()
                        .setUser(user)
                        .setName(alarmsDTO.getName())
                        .setStockName(alarmsDTO.getStockName())
                        .setVariance(alarmsDTO.getVariance())
        );
        return getAlarms(principal);
    }




}
