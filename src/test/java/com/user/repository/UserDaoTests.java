package com.user.repository;

import com.user.dao.UserDao;
import com.user.model.Userprofile;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserDaoTests {
    @Autowired
    private UserDao userDao;
    @Test
    public void UserProfile_GetAll_ReturnMoreThanOneUser() {
        Userprofile userprofile1 = Userprofile.builder().email("abcd@gmail.com").name("abcd")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();
        Userprofile userprofile2 = Userprofile.builder().email("efgh@gmail.com").name("efgh")
                .dob(new Date(2003, 12, 22))
                .phone("4578905").build();
        userDao.save(userprofile1);
        userDao.save(userprofile2);
        List<Userprofile> userprofileList = userDao.findAll();
        Assertions.assertThat(userprofileList).isNotNull();
        Assertions.assertThat(userprofileList.size()).isEqualTo(2);
    }
    @Test
    public void UserProfile_GetAllByEmail() {
        Userprofile userprofile = Userprofile.builder().email("hijk@gmail.com").name("hijk")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();
        userDao.save(userprofile);
        Userprofile userprofileList = userDao.findAllUserByEmail(userprofile.getEmail()).get(0);
        Assertions.assertThat(userprofileList).isNotNull();
}
    @Test
    public void UserDao_SaveAll_ReturnSavedUserProfile() {
        Userprofile userprofile = Userprofile.builder().email("hijk@gmail.com").name("hijk")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();

        Userprofile savedUser = userDao.save(userprofile);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void UserDao_UpdatePokemon_ReturnUserNotNull() {
        Userprofile userprofile = Userprofile.builder().email("hijk@gmail.com").name("hijk")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();

        userDao.save(userprofile);

        Userprofile userSave = userDao.findAllUserByEmail(userprofile.getEmail()).get(0);
        userSave.setName("testing");
        userSave.setPhone("6666666");

        Userprofile updatedUser = userDao.save(userSave);

        Assertions.assertThat(updatedUser.getName()).isEqualTo("testing");
        Assertions.assertThat(updatedUser.getPhone()).isEqualTo("6666666");
    }

    @Test
    public void UserDao_UserDelete_ReturnUserIsEmpty() {
        Userprofile userprofile = Userprofile.builder().email("hijk@gmail.com").name("hijk")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();

        userDao.save(userprofile);

       userDao.deleteByEmail(userprofile.getEmail());
        Optional<Userprofile> userReturn = userDao.findById(userprofile.getEmail());

        Assertions.assertThat(userReturn).isEmpty();
    }

}
