package cr.ac.una.progra4examen1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Progra4examen1Application {

    public static void main(String[] args) {
        // TEMPORAL - genera hash y lo imprime
        var encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        System.out.println("Hash 111: " + encoder.encode("111"));
        System.out.println("Hash 222: " + encoder.encode("222"));

        SpringApplication.run(Progra4examen1Application.class, args);
    }
}
