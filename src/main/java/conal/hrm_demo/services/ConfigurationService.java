package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.ScheduleTimeOptionRequest;
import conal.hrm_demo.entity.Configuration;

public interface ConfigurationService {
    void addDefaultScheduleTime();
    void updateScheduleTime(ScheduleTimeOptionRequest request);
    Configuration getScheduleTime();
    int getCurrentRangeTime();
}
