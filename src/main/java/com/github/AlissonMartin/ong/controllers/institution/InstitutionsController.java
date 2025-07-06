package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.RegisterInstitutionFormDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionUpdateFormDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.InstitutionService;
import com.github.AlissonMartin.ong.services.user.UserInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("institutionInstitutionsController")
@RequestMapping("/institution")
public class InstitutionsController {

    @Autowired
    UserInstitutionService userInstitutionService;

    @Autowired
    InstitutionService institutionService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Institution> create(@ModelAttribute RegisterInstitutionFormDTO body) {
        Institution institution = userInstitutionService.create(body);
        return ResponseEntity.ok(institution);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InstitutionDetailResponseDTO> update(@ModelAttribute InstitutionUpdateFormDTO body) {
        InstitutionDetailResponseDTO updated = institutionService.update(body);
        return ResponseEntity.ok(updated);
    }

}
