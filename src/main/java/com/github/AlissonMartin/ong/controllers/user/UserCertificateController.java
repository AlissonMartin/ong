package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.dtos.UserCertificateCreateRequestDTO;
import com.github.AlissonMartin.ong.dtos.UserCertificateResponseDTO;
import com.github.AlissonMartin.ong.services.user.UserCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/certificates")
public class UserCertificateController {
    @Autowired
    private UserCertificateService userCertificateService;

    @PostMapping
    public ResponseEntity<UserCertificateResponseDTO> create(@RequestBody UserCertificateCreateRequestDTO dto) {
        UserCertificateResponseDTO created = userCertificateService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserCertificateResponseDTO>> list() {
        return ResponseEntity.ok(userCertificateService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCertificateResponseDTO> getById(@PathVariable int id) {
        Optional<UserCertificateResponseDTO> result = userCertificateService.getById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCertificateResponseDTO> update(@PathVariable int id, @RequestBody UserCertificateCreateRequestDTO dto) {
        UserCertificateResponseDTO updated = userCertificateService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userCertificateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

