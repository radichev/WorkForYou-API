package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileDetailsRepository extends JpaRepository<UserProfileDetails, String> {

    UserProfileDetails findByFirstName(String firstName);

}
