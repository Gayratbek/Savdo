package uz.futbolteam.repo;

import org.springframework.data.repository.CrudRepository;
import uz.futbolteam.domain.Player;

import java.util.List;

public interface PlayerRepo extends CrudRepository<Player, Long> {
    List<Player> findByComment(String comment);
}
