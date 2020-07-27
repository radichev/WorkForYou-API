package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.SubSphere;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSphereRepository extends JpaRepository<SubSphere, String> {

    @Query("SELECT s FROM SubSphere s ORDER BY s.subSphere")
    List<SubSphere> findFiveSubSpheres(Pageable pageable);
}
