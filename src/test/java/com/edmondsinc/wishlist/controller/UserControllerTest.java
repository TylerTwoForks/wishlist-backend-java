package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.mock.MockData;
import com.edmondsinc.wishlist.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;


    MockData mockData = new MockData();

    @Test
    public void testGetAllUsers() throws Exception{
//       when(userService.getAllUsers()).thenReturn(mockData.userResponseDto);

//       mockMvc.perform(get("/users/all"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.userDtoList", Matchers.hasSize(2)))
//               .andExpect(jsonPath("$.userDtoList.[0].userName", Matchers.is("tyler@tyler.com")));
    }

}
