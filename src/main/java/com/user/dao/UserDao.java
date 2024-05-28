package com.user.dao;


import com.user.model.Userprofile;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Configuration
public interface UserDao extends JpaRepository<Userprofile, String> {
    public List<Userprofile> findAllUserByEmail(String email);
    public Userprofile findByEmail(String email);
    public Userprofile findAllById(Integer id);
    @Transactional
    void deleteByEmail(String email);


}
