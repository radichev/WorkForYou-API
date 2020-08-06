package com.radichev.workforyou.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.model.bindingModels.auth.SignUpBindingModel;
import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.viewModels.auth.SignUpViewModel;
import com.radichev.workforyou.repository.CertificateRepository;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.CertificateService;
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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("testUser")
public class CertificateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CertificateService certificateService;

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserProfileDetailsRepository userProfileDetailsRepository;

    private String TEST_CERTIFICATE1_ID, TEST_CERTIFICATE2_ID;
    private String TEST_certificateSubject1 = "Spring", TEST_certificateSubject2 = "Databases";
    private String TEST_awardedFrom1 = "Softuni", TEST_awardedFrom2 = "FMI";
    private int TEST_graduationYear1 = 2012, TEST_graduationYear2 = 2021;

    private String TEST_USER_ID;
    private String username = "Pesho";
    private String email = "pesho@abv.bg";
    private String password = "123456Jj";

    @BeforeEach
    public void setUp() {
        this.certificateRepository.deleteAll();
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();

        SignUpBindingModel signUpBindingModel = new SignUpBindingModel();
        signUpBindingModel.setUsername(username);
        signUpBindingModel.setEmail(email);
        signUpBindingModel.setPassword(password);

        TEST_USER_ID =  this.userService.signUpUser(signUpBindingModel).getId();

        Certificate certificate1 = new Certificate();
        certificate1.setCertificateSubject(TEST_certificateSubject1);
        certificate1.setAwardedFrom(TEST_awardedFrom1);
        certificate1.setGraduationYear(TEST_graduationYear1);
        certificate1 = this.certificateRepository.save(certificate1);
        TEST_CERTIFICATE1_ID = certificate1.getId();

        Certificate certificate2 = new Certificate();
        certificate2.setCertificateSubject(TEST_certificateSubject2);
        certificate2.setAwardedFrom(TEST_awardedFrom2);
        certificate2.setGraduationYear(TEST_graduationYear2);
        certificate2 = this.certificateRepository.save(certificate2);
        TEST_CERTIFICATE2_ID = certificate2.getId();
    }

    @AfterEach
    public void tearDown() {
        this.userRepository.deleteAll();
        this.certificateRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();
    }

    @Test
    public void testCertificatesReturnCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/api/certificates"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditCertificateShouldPerformCorrect() throws Exception {
        CertificateBindingModel testCertificate = new CertificateBindingModel();
        testCertificate.setCertificateSubject("test");
        testCertificate.setAwardedFrom("test");
        testCertificate.setGraduationYear(2020);

        this.mockMvc.perform(post("/api/certificates/edit/{certificateId}", TEST_CERTIFICATE1_ID)
                .content(asJsonString(testCertificate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.certificateSubject", is(testCertificate.getCertificateSubject())))
                .andExpect(jsonPath("$.awardedFrom", is(testCertificate.getAwardedFrom())))
                .andExpect(jsonPath("$.graduationYear", is(testCertificate.getGraduationYear())));
    }

    @Test
    public void testDeleteCertificateByIdShouldPerformCorrect() throws Exception {
        this.mockMvc.perform(delete("/api/certificates/{certificateId}", TEST_CERTIFICATE1_ID))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/certificates"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testAddCertificateShouldReturnCorrectStatusCode() throws Exception {
        CertificateBindingModel testCertificate = new CertificateBindingModel();
        testCertificate.setCertificateSubject("test");
        testCertificate.setAwardedFrom("test");
        testCertificate.setGraduationYear(2020);

        this.mockMvc.perform(post("/api/certificates/add/{userID}", TEST_USER_ID)
                .content(asJsonString(testCertificate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }





}
