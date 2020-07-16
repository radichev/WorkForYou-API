package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.SubSphere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSphereRepository extends JpaRepository<SubSphere, String> {
}
