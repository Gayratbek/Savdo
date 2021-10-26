package uz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.domain.User;

public interface UserRepo extends JpaRepository<User, Long > {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
