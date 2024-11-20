 package com.edmondsinc.wishlist.services;

 import com.edmondsinc.wishlist.mock.MockData;
 import com.edmondsinc.wishlist.model.User;
 import com.edmondsinc.wishlist.model.dto.response.UserResponseDto;
 import com.edmondsinc.wishlist.repository.UserRepo;
 import com.edmondsinc.wishlist.service.UserService;
 import org.assertj.core.api.Assertions;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.dao.DataAccessException;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.mockito.Mockito.when;


 @ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepo userRepo;

    MockData mockData = new MockData();

    @Test
    void contextLoads() {
        Assertions.assertThat(userService).isNotNull();
    }

    @Test
    void getAllUsers(){

        when(userRepo.findAll()).thenReturn(mockData.userList);

        UserResponseDto response = (UserResponseDto) userService.getAllUsers();

        assertEquals(2, response.getUserDtoList().size());
        assertEquals("tyler2@tyler2.com", response.getUserDtoList().get(1).getUserName());
    }
    
    @Test
    void createUserWithException(){;
        when(userRepo.save(Mockito.any(User.class))).thenThrow(new DataAccessException("test"){});

        try{
            UserResponseDto res = userService.createUser(mockData.createUserDto);
        }catch (Exception dae){
            assertEquals("Unable to save new user due to: test", dae.getMessage());
        }

    }




}
