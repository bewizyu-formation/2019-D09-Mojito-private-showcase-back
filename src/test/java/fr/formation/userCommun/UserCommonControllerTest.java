package fr.formation.userCommun;

import fr.formation.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserCommonControllerTest {

    @Test
    public void ok(){

    }
  /*  @Autowired
    private MockMvc mvc;

    @Before
    public void init(){
    }


    @Test
    public void listCommonUsers() throws Exception {
        mvc.perform(put("/common/?password=password")
                .header("Content-Type", "application/json")
                .content("{\"username\":\"bonjour\"," +
                        "\"email\":\"fake@email.com\"," +
                        "\"city\":\"Amiens\"," +
                        "\"adress\":\"1 rue de l'avenue\"" +
                        "}")
        )
                .andExpect(
                        status().isOk()
                ).andExpect(content().string("true"));

        mvc.perform(get("/common/")
                .header("Content-Type", "application/json"))
                .andExpect(
                        status().isOk()
                ).andExpect(content().json("[{\"username\":\"bonjour\"," +
                "\"email\":\"fake@email.com\"," +
                "\"city\":\"Amiens\"," +
                "\"adress\":\"1 rue de l'avenue\"" +
                "}]",false));
    }


    @Test
    public void getCommonUserInfoById() {
        Assertions.assertThat(true);
    }

    @Test
    public void getCommonUserInfoByUsername() throws Exception {
        mvc.perform(put("/common/?password=password")
                .header("Content-Type", "application/json")
                .content("{\"username\":\"bonjour\"," +
                        "\"email\":\"fake@email.com\"," +
                        "\"city\":\"Amiens\"," +
                        "\"adress\":\"1 rue de l'avenue\"" +
                        "}")
        )
                .andExpect(
                        status().isOk()
                ).andExpect(content().string("true"));

        mvc.perform(get("/common/")
                .header("Content-Type", "application/json"))
                .andExpect(
                        status().isOk()
                ).andExpect(content().json("[{\"username\":\"bonjour\"," +
                "\"email\":\"fake@email.com\"," +
                "\"city\":\"Amiens\"," +
                "\"adress\":\"1 rue de l'avenue\"" +
                "}]",false));
    }

    @Test
    public void registerUncompleteCommonUserShouldDoNothing() throws Exception{
        MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("admin")).andReturn();
        String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

        mvc.perform(put("/common/?password=bla")
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .content("{\"username\":\"bonjour\"}")
                )
                .andExpect(
                        status().isOk()
                ).andExpect(content().string("false"));
    }

    @Test
    public void registerCompleteCommonUser() throws Exception{

        mvc.perform(put("/common/?password=password")
                .header("Content-Type", "application/json")
                .content("{\"username\":\"bonjour\"," +
                        "\"email\":\"fake@email.com\"," +
                        "\"city\":\"Amiens\"," +
                        "\"adress\":\"1 rue de l'avenue\"" +
                        "}")
        )
                .andExpect(
                        status().isOk()
                ).andExpect(content().string("true"));

    }

    @Test
    public void registerCompleteCommonUserWithoutPassword() throws Exception{

        mvc.perform(put("/common/")
                .header("Content-Type", "application/json")
                .content("{\"username\":\"bonjour\"," +
                        "\"email\":\"fake@email.com\"," +
                        "\"city\":\"Amiens\"," +
                        "\"adress\":\"1 rue de l'avenue\"" +
                        "}")
        )
                .andExpect(
                        status().is4xxClientError()
                );
    }

    @Test
    public void modifyCommonUser() {
    }*/
}