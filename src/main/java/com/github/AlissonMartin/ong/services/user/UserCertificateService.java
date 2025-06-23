package com.github.AlissonMartin.ong.services.user;

import com.github.AlissonMartin.ong.dtos.UserCertificateCreateRequestDTO;
import com.github.AlissonMartin.ong.dtos.UserCertificateResponseDTO;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.models.UserCertificate;
import com.github.AlissonMartin.ong.repositories.UserCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCertificateService {
    @Autowired
    private UserCertificateRepository userCertificateRepository;

    public UserCertificateResponseDTO create(UserCertificateCreateRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        UserCertificate userCertificate = new UserCertificate();
        userCertificate.setUser(user);
        userCertificate.setCertificateName(dto.getName());
        userCertificate.setAcquiredAt(dto.getAcquiredAt());
        userCertificate.setExpirationDate(dto.getExpirationDate());
        userCertificate = userCertificateRepository.save(userCertificate);
        return toResponseDTO(userCertificate);
    }

    public Optional<UserCertificateResponseDTO> getById(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userCertificateRepository.findByIdAndUser(id, user).map(this::toResponseDTO);
    }

    public Iterable<UserCertificateResponseDTO> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Iterable<UserCertificate> all = userCertificateRepository.findByUser(user);
        List<UserCertificateResponseDTO> result = new ArrayList<>();
        for (UserCertificate uc : all) {
            result.add(toResponseDTO(uc));
        }
        return result;
    }

    public UserCertificateResponseDTO update(int id, UserCertificateCreateRequestDTO dto) {
        UserCertificate existing = userCertificateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("UserCertificate não encontrado"));
        existing.setCertificateName(dto.getName());
        existing.setAcquiredAt(dto.getAcquiredAt());
        existing.setExpirationDate(dto.getExpirationDate());
        existing = userCertificateRepository.save(existing);
        return toResponseDTO(existing);
    }

    private UserCertificateResponseDTO toResponseDTO(UserCertificate uc) {
        UserCertificateResponseDTO dto = new UserCertificateResponseDTO();
        dto.setId(uc.getId());
        dto.setCertificateName(uc.getCertificateName());
        dto.setUserId(uc.getUser().getId());
        dto.setAcquiredAt(uc.getAcquiredAt());
        dto.setExpirationDate(uc.getExpirationDate());
        return dto;
    }

    public void delete(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (userCertificateRepository.findByIdAndUser(id, user).isEmpty()) {
            throw new RuntimeException("UserCertificate não encontrado");
        }
        userCertificateRepository.deleteById(id);
    }

}
