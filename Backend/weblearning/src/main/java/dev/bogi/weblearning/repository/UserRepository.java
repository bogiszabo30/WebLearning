package dev.bogi.weblearning.repository;

import dev.bogi.weblearning.model.user.Role;
import dev.bogi.weblearning.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:role IS NULL OR :role MEMBER OF u.roles) AND " +
            "(:q IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', CAST(:q AS string), '%')) " +
            "  OR LOWER(u.email) LIKE LOWER(CONCAT('%', CAST(:q AS string), '%')))")
    Page<User> search(@Param("role") Role role, @Param("q") String q, Pageable pageable);
}

