package com.github.AlissonMartin.ong.services.institution;

import com.github.AlissonMartin.ong.services.UserAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.repositories.JobApplicationRepository;

@Service
public class InstitutionJobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private UserAchievementService userAchievementService;

    @Transactional
    public void updateStatus(int id, String status) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("JobApplication não encontrada"));
        jobApplication.setStatus(JobApplicationStatus.valueOf(status));
        if (status.equals("CHOSEN")) userAchievementService.FirstChosenApply(jobApplication.getUser());

        jobApplicationRepository.save(jobApplication);
    }
}


