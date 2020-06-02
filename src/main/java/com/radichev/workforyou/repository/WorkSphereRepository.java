package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.WorkSphere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSphereRepository extends JpaRepository<WorkSphere, String> {
}
