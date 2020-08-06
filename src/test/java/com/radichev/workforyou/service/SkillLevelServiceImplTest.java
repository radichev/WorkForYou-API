package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.SkillLevel;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;
import com.radichev.workforyou.repository.SkillLevelRepository;
import com.radichev.workforyou.service.serviceImpl.SkillLevelServiceImpl;
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
public class SkillLevelServiceImplTest {

    private SkillLevelService skillLevelService;

    @Mock
    SkillLevelRepository skillLevelRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private SkillLevel skillLevel;

    @BeforeEach
    public void setUp() {
        this.skillLevelService = new SkillLevelServiceImpl(skillLevelRepository, modelMapper);

        skillLevel = new SkillLevel();
        skillLevel.setSkillLevel("Expert");
    }

    @Test
    public void testFindAllSkillLevelsShouldReturnCorrectResult() {
        when(this.skillLevelRepository.findAll())
                .thenReturn(List.of(skillLevel));

        List<SkillLevelDto> testSkillLevelCollection = this.skillLevelService.findAllSkillLevels();

        Assertions.assertEquals(1, testSkillLevelCollection.size());
        SkillLevelDto testSkillLevel = testSkillLevelCollection.get(0);

        Assertions.assertEquals(skillLevel.getSkillLevel(), testSkillLevel.getSkillLevel());
    }

    @Test
    public void testFindSkillLevelByIdShouldReturnCorrectResult() {
        when(this.skillLevelRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(skillLevel));

        SkillLevel testSkillLevel = this.skillLevelService.findSkillLevelById("testId");

        Assertions.assertEquals(skillLevel.getSkillLevel(), testSkillLevel.getSkillLevel());
    }

    @Test
    public void testFindSkillLevelByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.skillLevelService.findSkillLevelById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "SkillLevel not found with testId id.");
    }

}
