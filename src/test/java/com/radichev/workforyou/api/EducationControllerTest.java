package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.Education;
import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.model.bindingModels.auth.SignUpBindingModel;
import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.repository.CountryRepository;
import com.radichev.workforyou.repository.EducationRepository;
import com.radichev.workforyou.repository.TitleTypeRepository;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.radichev.workforyou.api.TestUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("testUser")
public class EducationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    TitleTypeRepository titleTypeRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserProfileDetailsRepository userProfileDetailsRepository;

    private String TEST_USER_ID;
    private String username = "Pesho";
    private String email = "pesho@abv.bg";
    private String password = "123456Jj";

    private String TEST_COUNTRY_ID;
    private String TEST_COUNTRY = "Bulgaria";

    private String TEST_TITLETYPE_ID;
    private String TEST_TITLETYPE = "Ph.D";

    private String TEST_EDUCATION_ID;
    private String TEST_universityName = "Softuni";
    private String TEST_educationSubject = "Spring";
    private int TEST_graduationYear = 2012;

    ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        this.educationRepository.deleteAll();
        this.titleTypeRepository.deleteAll();
        this.countryRepository.deleteAll();
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();

        Country country = new Country(TEST_COUNTRY);
        this.countryRepository.save(country);

        TitleType titleType = new TitleType(TEST_TITLETYPE);
        this.titleTypeRepository.save(titleType);

        Education education = new Education();
        education.setCountry(country);
        education.setUniversityName(TEST_universityName);
        education.setEducationSubject(TEST_educationSubject);
        education.setTitleType(titleType);
        education.setGraduationYear(TEST_graduationYear);
        education = this.educationRepository.save(education);
        TEST_EDUCATION_ID = education.getId();

        SignUpBindingModel signUpBindingModel = new SignUpBindingModel();
        signUpBindingModel.setUsername(username);
        signUpBindingModel.setEmail(email);
        signUpBindingModel.setPassword(password);

        TEST_USER_ID =  this.userService.signUpUser(signUpBindingModel).getId();

    }

    @AfterEach
    public void tearDown() {
        this.educationRepository.deleteAll();
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();
    }

    @Test
    public void testEditEducationShouldPerformCorrect() throws Exception {
        TitleType titleType = new TitleType("BCC");
        this.titleTypeRepository.save(titleType);

        Country country = new Country("Island");
        this.countryRepository.save(country);

        EducationBindingModel testEducationBindingModel = new EducationBindingModel();
        testEducationBindingModel.setUniversityName("test");
        testEducationBindingModel.setEducationSubject("test");
        testEducationBindingModel.setGraduationYear(2019);
        testEducationBindingModel.setTitleType(this.modelMapper.map(titleType, TitleTypeDto.class));
        testEducationBindingModel.setCountry(this.modelMapper.map(country, CountryDto.class));

        this.mockMvc.perform(post("/api/educations/edit/{educationId}", TEST_EDUCATION_ID)
                .content(asJsonString(testEducationBindingModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.universityName", is(testEducationBindingModel.getUniversityName())))
                .andExpect(jsonPath("$.educationSubject", is(testEducationBindingModel.getEducationSubject())))
                .andExpect(jsonPath("$.graduationYear", is(testEducationBindingModel.getGraduationYear())))
                .andExpect(jsonPath("$.titleType.titleType", is(testEducationBindingModel.getTitleType().getTitleType())))
                .andExpect(jsonPath("$.country.country", is(testEducationBindingModel.getCountry().getCountry())));
    }

    @Test
    public void testAddEducationShouldReturnCorrectStatusCode() throws Exception {
        TitleType titleType = new TitleType("BCC");
        this.titleTypeRepository.save(titleType);

        Country country = new Country("Island");
        this.countryRepository.save(country);

        EducationBindingModel testEducationBindingModel = new EducationBindingModel();
        testEducationBindingModel.setUniversityName("test");
        testEducationBindingModel.setEducationSubject("test");
        testEducationBindingModel.setGraduationYear(2019);
        testEducationBindingModel.setTitleType(this.modelMapper.map(titleType, TitleTypeDto.class));
        testEducationBindingModel.setCountry(this.modelMapper.map(country, CountryDto.class));

        this.mockMvc.perform(post("/api/educations/add/{userId}", TEST_USER_ID)
                .content(asJsonString(testEducationBindingModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteEducationByIdShouldPerformCorrect() throws Exception {
        this.mockMvc.perform(delete("/api/educations/{educationId}", TEST_EDUCATION_ID))
                .andExpect(status().isOk());
    }
}
