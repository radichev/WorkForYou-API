package com.radichev.workforyou.service;

import com.google.common.collect.Sets;
import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;
import com.radichev.workforyou.repository.WorkSphereRepository;
import com.radichev.workforyou.service.serviceImpl.WorkSphereServiceImpl;
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
public class WorkSphereServiceImplTest {

    private WorkSphereService workSphereService;

    @Mock
    private WorkSphereRepository workSphereRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private WorkSphere workSphere;

    @BeforeEach
    public void setUp() {
        this.workSphereService = new WorkSphereServiceImpl(workSphereRepository, modelMapper);

        workSphere = new WorkSphere();
        workSphere.setWorkSphere("Gaming");

        SubSphere subSphere = new SubSphere("Game Developer");
        workSphere.setSubSpheres(Sets.newHashSet(subSphere));
    }

    @Test
    public void testFindWorkSphereByIdShouldReturnCorrectResult() {
        when(this.workSphereRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(workSphere));

        WorkSphere testWorkSphere = this.workSphereService.findWorkSphereById("testId");

        Assertions.assertEquals(workSphere.getWorkSphere(), testWorkSphere.getWorkSphere());
        Assertions.assertEquals(workSphere.getSubSpheres().size(), testWorkSphere.getSubSpheres().size());
    }

    @Test
    public void testFindWorkSphereByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.workSphereService.findWorkSphereById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "WorkSphere not found with testId id.");
    }

    @Test
    public void testFindAllWorkSpheresShouldReturnCorrectResult() {
        when(this.workSphereRepository.findAll())
                .thenReturn(List.of(workSphere));

        List<WorkSphereDto> testWorkSphereCollection = this.workSphereService.findAllWorkSpheres();
        Assertions.assertEquals(1, testWorkSphereCollection.size());

        WorkSphereDto testWorkSphere = testWorkSphereCollection.get(0);
        Assertions.assertEquals(workSphere.getWorkSphere(), testWorkSphere.getWorkSphere());
        Assertions.assertEquals(workSphere.getSubSpheres().size(), testWorkSphere.getSubSpheres().size());
    }
}
