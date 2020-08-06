package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.repository.TitleTypeRepository;
import com.radichev.workforyou.service.serviceImpl.TitleTypeServiceImpl;
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
public class TitleTypeServiceImplTest {

    private TitleTypeService titleTypeService;

    @Mock
    private TitleTypeRepository titleTypeRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private TitleType titleType;

    @BeforeEach
    public void setUp() {
        this.titleTypeService = new TitleTypeServiceImpl(titleTypeRepository, modelMapper);

        titleType = new TitleType("Ph.D");
    }

    @Test
    public void testFindAllTitleTypesShouldReturnCorrectResult() {
        when(this.titleTypeRepository.findAll())
                .thenReturn(List.of(titleType));

        List<TitleTypeDto> testTitleTypeCollection = this.titleTypeService.findAllTitleTypes();
        Assertions.assertEquals(1, testTitleTypeCollection.size());

        TitleTypeDto testTitleType = testTitleTypeCollection.get(0);

        Assertions.assertEquals(titleType.getTitleType(), testTitleType.getTitleType());
    }

    @Test
    public void testFindTitleTypeByIdShouldReturnCorrectResult() {
        when(this.titleTypeRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(titleType));

        TitleType testTitleType = this.titleTypeService.findTitleTypeById("testId");

        Assertions.assertEquals(titleType.getTitleType(), testTitleType.getTitleType());
    }

    @Test
    public void testFindTitleTypeByIdShouldThrowEntityNotFoundExceptionWithIncorrectId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.titleTypeService.findTitleTypeById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "TitleType not found with testId id.");
    }

}
