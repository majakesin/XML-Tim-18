package com.user.manager.repository;

import com.user.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
    List<User> findAllByAdminApprovedFalse();
    List<User> findAllByAdminApprovedTrueAndIsRemovedFalse();
}
