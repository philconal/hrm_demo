package conal.hrm_demo.controller;


import conal.hrm_demo.dto.request.ScheduleTimeOptionRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.entity.Configuration;
import conal.hrm_demo.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.PUT, value = "/configuration")
    public ApplicationDataResponse<String> updateScheduleTime(
            @RequestBody ScheduleTimeOptionRequest request
    ) {
        configurationService.updateScheduleTime(request);
        return new ApplicationDataResponse<>(HttpStatus.OK, "Update Schedule successfully!!");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/configuration")
    public ApplicationDataResponse<Configuration> getScheduleTime() {
        return new ApplicationDataResponse<>(HttpStatus.OK, configurationService.getScheduleTime());
    }
}
