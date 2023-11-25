package club.pard.server.ftiland.oldnew.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.ftiland.oldnew.entity.user.User;


public interface UserRepository extends JpaRepository<User, Long>{
    public Boolean existsByUserid(String userid);
    public Optional<User> findByUserid(String userid);
}
