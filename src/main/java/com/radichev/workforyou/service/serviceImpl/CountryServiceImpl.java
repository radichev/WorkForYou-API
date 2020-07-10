package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.repository.CountryRepository;
import com.radichev.workforyou.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCountries() {
        if (this.countryRepository.count() == 0) {
            this.countryRepository.saveAndFlush(new Country("Afghanistan"));
            this.countryRepository.saveAndFlush(new Country("Albania"));
            this.countryRepository.saveAndFlush(new Country("Algeria"));
            this.countryRepository.saveAndFlush(new Country("Andorra"));
            this.countryRepository.saveAndFlush(new Country("Angola"));
            this.countryRepository.saveAndFlush(new Country("Antigua and Barbuda"));
            this.countryRepository.saveAndFlush(new Country("Argentina"));
            this.countryRepository.saveAndFlush(new Country("Armenia"));
            this.countryRepository.saveAndFlush(new Country("Australia"));
            this.countryRepository.saveAndFlush(new Country("Austria"));
            this.countryRepository.saveAndFlush(new Country("Azerbaijan"));
            this.countryRepository.saveAndFlush(new Country("Bahamas"));
            this.countryRepository.saveAndFlush(new Country("Bahrain"));
            this.countryRepository.saveAndFlush(new Country("Bangladesh"));
            this.countryRepository.saveAndFlush(new Country("Barbados"));
            this.countryRepository.saveAndFlush(new Country("Belarus"));
            this.countryRepository.saveAndFlush(new Country("Belgium"));
            this.countryRepository.saveAndFlush(new Country("Belize"));
            this.countryRepository.saveAndFlush(new Country("Benin"));
            this.countryRepository.saveAndFlush(new Country("Bhutan"));
            this.countryRepository.saveAndFlush(new Country("Bolivia"));
            this.countryRepository.saveAndFlush(new Country("Bosnia and Herzegovina"));
            this.countryRepository.saveAndFlush(new Country("Botswana"));
            this.countryRepository.saveAndFlush(new Country("Brazil"));
            this.countryRepository.saveAndFlush(new Country("Brunei"));
            this.countryRepository.saveAndFlush(new Country("Bulgaria"));
        }
    }

    @Override
    public List<CountryDto> findAllCountries() {
        return this.countryRepository.findAll()
                .stream()
                .map(country -> this.modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Country findByCountryName(String countryName) {
        return this.countryRepository.findByCountry(countryName);
    }
}
