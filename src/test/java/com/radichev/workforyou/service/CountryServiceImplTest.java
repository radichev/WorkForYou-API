package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.repository.CountryRepository;
import com.radichev.workforyou.service.serviceImpl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        this.countryService = new CountryServiceImpl(countryRepository, modelMapper);
    }

    @Test
    public void testFindAllCountriesShouldReturnCorrectResult() {
        Country country = new Country("Bulgaria");
        Country country2 = new Country("England");

        when(this.countryRepository.findAll())
                .thenReturn(List.of(country, country2));

        List<CountryDto> allCountries = this.countryService.findAllCountries();

        Assertions.assertEquals(2, allCountries.size());

        CountryDto countryDto = allCountries.get(0);
        CountryDto countryDto2 = allCountries.get(1);

        Assertions.assertEquals(country.getCountry(), countryDto.getCountry());
        Assertions.assertEquals(country2.getCountry(), countryDto2.getCountry());
    }

    @Test
    public void testFindCountryByIdShouldReturnCorrectResult() {
        Country country = new Country("Bulgaria");

        when(this.countryRepository.findById("testId"))
                .thenReturn(Optional.of(country));

        Country testCountry = this.countryService.findCountryById("testId");

        Assertions.assertEquals(country.getCountry(), testCountry.getCountry());
    }

    @Test
    public void testFindCountryByIdShouldThrowInvalidEntityExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.countryService.findCountryById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Country not found with testId id.");
    }
}
