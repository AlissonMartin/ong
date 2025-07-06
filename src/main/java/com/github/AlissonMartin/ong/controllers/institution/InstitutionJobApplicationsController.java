package com.github.AlissonMartin.ong.controllers.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.AlissonMartin.ong.services.institution.InstitutionJobApplicationService;

@RestController
@RequestMapping("/institution/job_applications")
public class InstitutionJobApplicationsController {
    @Autowired
    private InstitutionJobApplicationService service;

    @PutMapping("/{id}/reject")
    public void reject(@PathVariable int id) {
        service.updateStatus(id, "REJECTED");
    }

    @PutMapping("/{id}/choose")
    public void choose(@PathVariable int id) {
        service.updateStatus(id, "CHOSEN");
    }
}
