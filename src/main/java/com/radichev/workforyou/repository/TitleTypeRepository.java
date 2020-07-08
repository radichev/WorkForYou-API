package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleTypeRepository extends JpaRepository<TitleType, String> {
}
