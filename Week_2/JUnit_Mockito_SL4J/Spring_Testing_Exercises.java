// Spring Testing Exercises Combined

// Common Spring + JPA + Web imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// ==========================
// Exercise 1: Basic Unit Test for a Service Method
// ==========================
@Service
class CalculatorService {
    public int add(int a, int b) {
        return a + b;
    }
}

class CalculatorServiceTest {
    @Test
    void testAdd() {
        CalculatorService service = new CalculatorService();
        assertEquals(5, service.add(2, 3));
    }
}

// ==========================
// Exercise 2: Mocking a Repository in a Service Test
// ==========================
@Entity
class User {
    @Id
    private Long id;
    private String name;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
    List<User> findByName(String name);
}

@Service
class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }
}

class UserServiceTest {
    @Test
    void testGetUserById() {
        UserRepository mockRepo = mock(UserRepository.class);
        User user = new User(); user.setId(1L); user.setName("John");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        UserService service = new UserService();
        service.userRepository = mockRepo;

        User result = service.getUserById(1L);
        assertEquals("John", result.getName());
    }
}

// ==========================
// Exercise 3: Testing a REST Controller with MockMvc
// ==========================
@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }
}

// ==========================
// Exercises 4, 5, 8: Integration + Controller Tests
// ==========================
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser() throws Exception {
        User user = new User(); user.setId(1L); user.setName("Alice");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User(); user.setId(1L); user.setName("Bob");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Bob\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void testUserNotFoundException() throws Exception {
        when(userService.getUserById(99L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}

// ==========================
// Exercise 6: Test Service Exception Handling
// ==========================
class UserServiceExceptionTest {
    @Test
    void testMissingUserThrowsException() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findById(100L)).thenReturn(Optional.empty());

        UserService service = new UserService();
        service.userRepository = mockRepo;

        assertThrows(NoSuchElementException.class, () -> service.getUserById(100L));
    }
}

// ==========================
// Exercise 7: Test Custom Repository Query
// ==========================
class CustomQueryTest {
    @Test
    void testFindByName() {
        UserRepository mockRepo = mock(UserRepository.class);
        User user = new User(); user.setId(2L); user.setName("Eve");

        when(mockRepo.findByName("Eve")).thenReturn(List.of(user));

        UserService service = new UserService();
        service.userRepository = mockRepo;

        List<User> users = service.getUsersByName("Eve");
        assertEquals(1, users.size());
        assertEquals("Eve", users.get(0).getName());
    }
}

// ==========================
// Exercise 8: Global Exception Handler
// ==========================
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}

// ==========================
// Exercise 9: Parameterized Test
// ==========================
class CalculatorParameterizedTest {

    CalculatorService calculator = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
        "1,2,3",
        "10,15,25",
        "100,200,300"
    })
    void testAddMultiple(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
}
