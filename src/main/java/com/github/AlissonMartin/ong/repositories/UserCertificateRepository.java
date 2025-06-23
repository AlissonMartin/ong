package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.models.UserCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCertificateRepository extends CrudRepository<UserCertificate, Integer> {

    Optional<UserCertificate> findByIdAndUser(int id, User user);

    List<UserCertificate> findByUser(User user);
}
