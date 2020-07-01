package rent.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rent.app.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {
    Client findByUsername(String s);
}
