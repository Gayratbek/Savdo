package uz.futbolteam.repo;

import org.springframework.data.repository.CrudRepository;
import uz.futbolteam.domain.Message;
import uz.futbolteam.domain.Player;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
