package de.jos.project.database;

        import de.jos.project.model.User;
        import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{
}
