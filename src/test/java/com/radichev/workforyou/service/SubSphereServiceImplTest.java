package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.WorkSphereDto.SubSphereDto;
import com.radichev.workforyou.repository.SubSphereRepository;
import com.radichev.workforyou.service.serviceImpl.SubSphereServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubSphereServiceImplTest {

    private SubSphereService subSphereService;

    @Mock
    private SubSphereRepository subSphereRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private SubSphere subSphere;

    @BeforeEach
    public void setUp() {
        this.subSphereService = new SubSphereServiceImpl(subSphereRepository, modelMapper);

        subSphere = new SubSphere("Web Programming");
    }

    @Test
    public void testFindSubSphereByIdShouldReturnCorrectResult() {
        when(this.subSphereRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(subSphere));

        SubSphere testSubSphere = this.subSphereService.findSubSphereById("testId");

        Assertions.assertEquals(subSphere.getSubSphere(), testSubSphere.getSubSphere());
    }

    @Test
    public void testFindSubSphereByIdShouldThrowEntityNotFoundExceptionWithIncorrectId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.subSphereService.findSubSphereById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "SubSphere not found with testId id.");
    }

    @Test
    public void testFindFiveSubSpheresShouldReturnCorrectResult() {
        when(this.subSphereRepository.findFiveSubSpheres(PageRequest.of(0, 5)))
                .thenReturn(List.of(subSphere, subSphere, subSphere, subSphere, subSphere));

        List<SubSphereDto> testSubSphereCollection = this.subSphereService.findFiveSubSpheres();
        Assertions.assertEquals(5, testSubSphereCollection.size());

        SubSphereDto testSubSphere = testSubSphereCollection.get(0);
        SubSphereDto testSubSphere2 = testSubSphereCollection.get(3);

        Assertions.assertEquals(subSphere.getSubSphere(), testSubSphere.getSubSphere());
        Assertions.assertEquals(subSphere.getSubSphere(), testSubSphere2.getSubSphere());
    }

}
