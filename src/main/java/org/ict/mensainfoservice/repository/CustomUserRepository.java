package org.ict.mensainfoservice.repository;

import org.ict.mensainfoservice.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
}
