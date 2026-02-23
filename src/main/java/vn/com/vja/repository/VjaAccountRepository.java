package vn.com.vja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.vja.entity.VjaAccount;

import java.util.Date;
import java.util.Optional;

/**
* VjaAccountRepository
* QuangDK.
*/
@Repository
public interface VjaAccountRepository extends JpaRepository<VjaAccount, Long> {


    Optional<VjaAccount> findVjaAccountByUsernameAndDeletedDateIsNull(String username);
}
