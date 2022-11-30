package conal.hrm_demo.services;


import conal.hrm_demo.dto.request.ScheduleTimeOptionRequest;
import conal.hrm_demo.entity.Configuration;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    private static final Long DEFAULT_SCHEDULE_TIME = 300000L;
    private int CURRENT_RANGE_TIME = 5;
    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public void addDefaultScheduleTime() {
        Configuration configuration =
                Configuration
                        .builder()
                        .scheduleTimeRepeat(DEFAULT_SCHEDULE_TIME)
                        .createdDate(new Date())
                        .updatedDate(new Date())
                        .build();
        configurationRepository.save(configuration);
    }

    @Override
    public void updateScheduleTime(ScheduleTimeOptionRequest request) {
        if (request.getScheduleOption() == null || request.getScheduleOption().trim().isBlank())
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Schedule Option is required");
        if (request.getTime() % CURRENT_RANGE_TIME != 0)
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Schedule Time must be common multiples by " + CURRENT_RANGE_TIME);
        Configuration scheduleTime = getScheduleTime();
        Long scheduleTimeRequest = scheduleTime.getScheduleTimeRepeat();
        switch (request.getScheduleOption()) {
            case "MINUTE" -> scheduleTimeRequest = request.getTime() * 3600000L;
            case "HOUR" -> scheduleTimeRequest = 3L;
            case "WEEK" -> scheduleTimeRequest = 4L;
            case "MONTH" -> scheduleTimeRequest = 5L;
            case "YEAR" -> scheduleTimeRequest = 6L;
        }
        scheduleTime.setScheduleTimeRepeat(scheduleTimeRequest);
        scheduleTime.setUpdatedDate(new Date());
        CURRENT_RANGE_TIME = request.getTime();
        configurationRepository.save(scheduleTime);

    }

    @Override
    public Configuration getScheduleTime() {
        return configurationRepository.findAll().stream().findFirst().orElse(Configuration.builder().scheduleTimeRepeat(DEFAULT_SCHEDULE_TIME).createdDate(new Date()).updatedDate(new Date()).build());
    }

    @Override
    public int getCurrentRangeTime() {
        return CURRENT_RANGE_TIME;
    }
}
