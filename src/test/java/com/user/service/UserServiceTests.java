package com.user.service;

import com.user.dao.UserDao;
import com.user.exceptions.InvalidDataException;
import com.user.exceptions.UserNotFoundException;
import com.user.model.Userprofile;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;
    @Test
    public void UserService_GetAllUser_ReturnsMoreThanOne() {
        Userprofile userprofile1 = Userprofile.builder().email("abcd@gmail.com").name("abcd")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();
        Userprofile userprofile2 = Userprofile.builder().email("efgh@gmail.com").name("efgh")
                .dob(new Date(2003, 12, 22))
                .phone("4578905").build();
        when(userDao.save(Mockito.any(Userprofile.class))).thenReturn(userprofile1);
        userDao.save(userprofile1);
        when(userDao.save(Mockito.any(Userprofile.class))).thenReturn(userprofile2);
        userDao.save(userprofile2);

        List<Userprofile> userprofileList = userService.getAllUsers();
        Assertions.assertThat(userprofileList).isNotNull();
        Assertions.assertThat(userprofileList.size()).isEqualTo(2);

    }
    @SneakyThrows
    @Test
    public void UserService_FindByEmail_ReturnUser() {
        String email = "testing@gamil.com";
        Userprofile userprofile = Userprofile.builder().email("testing@gamil.com").name("abcd")
                .dob(new Date(2000, 11, 21)).build();
        when(userDao.findByEmail(email)).thenReturn(userprofile);
        Userprofile returnUser = userService.getUserByEmail(email);
        Assertions.assertThat(returnUser).isNotNull();
    }
    @Test void UserService_AddUser_ReturnUser() {
        Userprofile userprofile = Userprofile.builder().email("abcd@gmail.com").name("abcd")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();

        when(userDao.save(userprofile)).thenReturn(userprofile);
        userService.addUser(userprofile);
        List<Userprofile> userprofileList = userService.getAllUsers();
        Assertions.assertThat(userprofileList).isNotNull();
    }
    @SneakyThrows
    @Test
    public void UserService_UpdateUser_ReturnPokemonDto() {
        String email = "testing@gmail.com";
        Userprofile userprofile = Userprofile.builder().email("testing@gmail.com").name("abcd")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();
        when(userDao.findByEmail(email)).thenReturn(userprofile);
//        when(userDao.save(userprofile)).thenReturn(userprofile);
        doNothing().when(userDao).save(userprofile);
        userprofile.setName("testing");
//        assertAll(() -> userDao.updateUser(email, userprofile));
        userService.updateUser(email,userprofile);
        Userprofile userprofileList = userService.getUserByEmail(email);
        Assertions.assertThat(userprofileList.getName()).isEqualTo("testing");
    }
    @Test
    public void UserService_DeleteUserByEmail_ReturnVoid() {
        String email = "testing@gmail.com";
        Userprofile userprofile = Userprofile.builder().email("testing@gmail.com").name("abcd")
                .dob(new Date(2000, 11, 21))
                .phone("122345677").build();
        when(userDao.findByEmail(email)).thenReturn(userprofile);
        doNothing().when(userDao).delete(userprofile);

        assertAll(() -> userDao.deleteByEmail(email));
    }

}
