package pl.szlify.codingapi.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szlify.codingapi.model.dto.LoginRequest;
import pl.szlify.codingapi.model.dto.StudentRegistrationDto;
import pl.szlify.codingapi.model.dto.TeacherRegistrationDto;
import pl.szlify.codingapi.security.JwtTokenProvider;
import pl.szlify.codingapi.service.StudentService;
import pl.szlify.codingapi.service.TeacherService;
import org.springframework.security.core.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(request.getUsername());

            // Pobranie szczegółów użytkownika
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            // Tworzymy odpowiedź zawierającą token i rolę użytkownika
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("⛔ Niepoprawne dane logowania");
        }
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(@Valid @RequestBody StudentRegistrationDto studentDto) {
        studentService.registerStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully!");
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<String> registerTeacher(@Valid @RequestBody TeacherRegistrationDto teacherDto) {
        teacherService.registerTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher registered successfully!");
    }
}
