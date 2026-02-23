package vn.com.vja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.vja.entity.VjaPassword;

import java.util.Optional;

/**
* VjaPasswordRepository
* QuangDK.
*/
@Repository
public interface VjaPasswordRepository extends JpaRepository<VjaPassword, Long> {

    Optional<VjaPassword> findVjaPasswordByAccountId(Long accountId);
}
