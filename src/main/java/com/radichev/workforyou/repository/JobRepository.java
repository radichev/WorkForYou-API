package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    @Query("SELECT j FROM Job as j WHERE j.userProfileDetails.user.id = :userId")
    List<Job> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT j FROM Job as j WHERE j.subSphere.subSphere = :subSphere ORDER BY j.jobTitle ASC")
    List<Job> findFiveJobsBySubSphere(@Param("subSphere") String subSphere, Pageable pageable);

    @Query("SELECT j FROM Job as j WHERE j.subSphere.id = :subSphereId ORDER BY j.jobTitle ASC")
    Page<Job> findAllJobsBySubSphereId(@Param("subSphereId") String subSphereId, Pageable pageable);

    @Query("SELECT j FROM Job as j join j.boughtByUser boughtByUser WHERE boughtByUser.user.id = :userId")
    List<Job> findJobsBoughtByUserId(@Param("userId") String userId);

}
