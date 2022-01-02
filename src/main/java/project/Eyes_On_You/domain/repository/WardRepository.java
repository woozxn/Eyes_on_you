package project.Eyes_On_You.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.Eyes_On_You.domain.entity.Ward;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward,Long>, JpaSpecificationExecutor<Ward> {
    List<Ward> findByDetectedTrue();
}
