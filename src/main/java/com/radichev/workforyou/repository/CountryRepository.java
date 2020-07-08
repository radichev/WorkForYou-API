package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
