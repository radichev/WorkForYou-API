package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.domain.entity.Skill;
import com.radichev.workforyou.domain.entity.SkillLevel;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;
import com.radichev.workforyou.repository.SkillRepository;
import com.radichev.workforyou.service.serviceImpl.SkillServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    private SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private UserProfileDetailsService userProfileDetailsService;

    @Mock
    private SkillLevelService skillLevelService;

    private ModelMapper modelMapper = new ModelMapper();
    private Skill skill;
    private UserProfileDetails userProfileDetails;
    private SkillLevel testSkillLevel;
    private SkillBindingModel skillBindingModel;
    private SkillLevel skillLevel;

    @BeforeEach
    public void setUp() {
        this.skillService = new SkillServiceImpl(skillRepository, modelMapper, userProfileDetailsService, skillLevelService);

        skill = new Skill();
        skill.setSkill("Spring Data");
        skillLevel = new SkillLevel("Expert");
        skill.setSkillLevel(skillLevel);

        testSkillLevel = new SkillLevel("Basic");

        skillBindingModel = new SkillBindingModel();
        skillBindingModel.setSkill("C# Master");
        skillBindingModel.setSkillLevel(this.modelMapper.map(testSkillLevel, SkillLevelDto.class));

        userProfileDetails = new UserProfileDetails();
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");
    }

    @Test
    public void testAddSkillShouldReturnCorrectResult() {
        when(this.userProfileDetailsService.findUserProfileDetailsById("testId"))
                .thenReturn(userProfileDetails);

        when(this.skillRepository.saveAndFlush(Mockito.any(Skill.class)))
                .thenReturn(skill);

        when(this.skillLevelService.findSkillLevelById(skill.getSkillLevel().getId()))
                .thenReturn(skillLevel);

        SkillBindingModel skillBindingModel = this.modelMapper.map(skill, SkillBindingModel.class);

        SkillDto testSkill = this.skillService.addSkill(skillBindingModel, "testId");

        Assertions.assertEquals(skill.getSkill(), testSkill.getSkill());
        Assertions.assertEquals(skill.getSkillLevel().getSkillLevel(), testSkill.getSkillLevel().getSkillLevel());
    }

    @Test
    public void testFindSkillByIdShouldReturnCorrectResult() {
        when(this.skillRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(skill));

        Skill testSkill = this.skillService.findSkillById("testId");

        Assertions.assertEquals(skill.getSkill(), testSkill.getSkill());
        Assertions.assertEquals(skill.getSkillLevel().getSkillLevel(), testSkill.getSkillLevel().getSkillLevel());
    }

    @Test
    public void testFindSkillByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.skillService.findSkillById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Skill not found with testId id.");
    }

    @Test
    public void testEditSkillByIdShouldReturnCorrectResult() {
        when(this.skillRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(skill));

        when(this.skillRepository.save(Mockito.any(Skill.class)))
                .thenReturn(this.modelMapper.map(skillBindingModel, Skill.class));

        when(this.skillLevelService.findSkillLevelById(skill.getSkillLevel().getId()))
                .thenReturn(testSkillLevel);

        SkillDto testSkill = this.skillService.editSkillById("testId", skillBindingModel);

        Assertions.assertEquals(skillBindingModel.getSkill(), testSkill.getSkill());
        Assertions.assertEquals(skillBindingModel.getSkillLevel().getSkillLevel(), testSkill.getSkillLevel().getSkillLevel());
    }
}
