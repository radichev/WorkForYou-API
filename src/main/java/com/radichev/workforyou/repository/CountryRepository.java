package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    Country findByCountry(String countryName);
}
