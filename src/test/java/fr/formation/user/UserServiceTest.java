package fr.formation.user;

import fr.formation.event.Event;
import fr.formation.event.EventDaoRepository;
import fr.formation.event.EventService;
import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private EventDaoRepository EventDaoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @InjectMocks
    private EventService eventService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEventUsers() throws NullPointerException {

        userService.findAll().size();

        User user = new User();
        user.setUsername("jeanne");
        user.setPassword("trombonNe45");
        user.setId(111L);
        userRepository.save(user);

        Event event = new Event();
        event.setId(10L);
        event.setOwner(user);

        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019,10,2), LocalTime.of(15,15));
        event.setDate(date);
        event.setNbPlace(15);

        EventDaoRepository.save(event);
        userRepository.save(user);

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));
        Assertions.assertThat(userService.getUserById(111L)).isEqualTo(user);

        Mockito.when(EventDaoRepository.findById(10L)).thenReturn(Optional.of(event));
        Assertions.assertThat(eventService.getEventById(10L)).isEqualTo(event);

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));

        /**userService.addEvent(
                user.getId(),event
        );

        Assertions.assertThat(userService.getUserById(111L).getUsername()== "jeanne");
        Assertions.assertThat(eventService.listEvents().size()==1);
        Assertions.assertThat(userService.getUserById(111L).getEvents().size()>0);

        /**
         //EventDaoRepository.save(event);
         //EventDaoRepository.save(event);
         userService.addEvent(user.getId(), event);
         Assertions.assertThat(eventService.listEvents().size()==1);
         Assertions.assertThat(userService.getUserById(111L).getEvents().size()>0);
         */
    }



}