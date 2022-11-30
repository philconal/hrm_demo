package conal.hrm_demo.entity.enums;

public enum ScheduleTimeOption {
    MINUTE("MINUTE"),
    HOUR("HOUR"),
    WEEK("WEEK"),
    MONTH("MONTH");
    private final String scheduleTime;

    ScheduleTimeOption(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }
}
