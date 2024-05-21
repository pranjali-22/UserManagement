package com.user.dao;


import com.user.model.Userentity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Userentity, String> {
    public Userentity findUserByEmail(String email);
    @Transactional
    void deleteByEmail(String email);


}
