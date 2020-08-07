package com.radichev.workforyou.api;

import com.google.common.collect.Sets;
import com.radichev.workforyou.domain.entity.*;
import com.radichev.workforyou.model.bindingModels.SubSphereBindingModel;
import com.radichev.workforyou.model.bindingModels.auth.SignUpBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.repository.*;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.radichev.workforyou.api.TestUtils.asJsonString;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("testUser")
public class JobControllerTest {

    private final MockMvc mockMvc;
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final WorkSphereRepository workSphereRepository;
    private final SubSphereRepository subSphereRepository;
    private final UserService userService;
    private final UserProfileDetailsService userProfileDetailsService;
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public JobControllerTest(MockMvc mockMvc,
                             JobRepository jobRepository,
                             ModelMapper modelMapper,
                             WorkSphereRepository workSphereRepository,
                             SubSphereRepository subSphereRepository,
                             UserService userService,
                             UserProfileDetailsService userProfileDetailsService,
                             UserProfileDetailsRepository userProfileDetailsRepository,
                             UserRepository userRepository,
                             RoleRepository roleRepository) {
        this.mockMvc = mockMvc;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.workSphereRepository = workSphereRepository;
        this.subSphereRepository = subSphereRepository;
        this.userService = userService;
        this.userProfileDetailsService = userProfileDetailsService;
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private String JOB_ID;
    private String TEST_USER_ID;
    private String WORKSPHERE_ID;
    private String SUBSPHERE_ID;
    private Job job;
    private SubSphere subSphere;
    private UserProfileDetails userProfileDetails;
    private final String VALID_FILE_NAME = "validFileName";
    private final String VALID_FILE_TYPE = IMAGE_JPEG.getMimeType();
    private final byte[] VALID_DATA = new byte[]{11, 11};

    @BeforeEach
    public void setUp() {
        this.jobRepository.deleteAll();
        this.workSphereRepository.deleteAll();
        this.subSphereRepository.deleteAll();
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();

        SignUpBindingModel signUpBindingModel = new SignUpBindingModel();
        String username = "Pesho";
        signUpBindingModel.setUsername(username);
        String email = "pesho@abv.bg";
        signUpBindingModel.setEmail(email);
        String password = "123456Jj";
        signUpBindingModel.setPassword(password);

        TEST_USER_ID = this.userService.signUpUser(signUpBindingModel).getId();

        Country country = new Country("Bulgaria");

        userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(TEST_USER_ID);
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Ivanov");
        userProfileDetails.setCountry(country);
        this.userProfileDetailsRepository.save(userProfileDetails);

        WorkSphere workSphere = new WorkSphere();
        workSphere.setWorkSphere("Programming");
        WORKSPHERE_ID = this.workSphereRepository.save(workSphere).getId();

        subSphere = new SubSphere("WebDevelopment");
        SUBSPHERE_ID = this.subSphereRepository.save(subSphere).getId();

        job = new Job();
        job.setJobTitle("Spring Dev");
        job.setDescription("Very good spring developer here!");
        job.setDeliveryTime(23);
        job.setPrice(BigDecimal.valueOf(1234));
        job.setJobPicture("job picture");
        job.setWorkSphere(workSphere);
        job.setSubSphere(subSphere);
        job.setUserProfileDetails(userProfileDetails);
        job = this.jobRepository.save(job);
        JOB_ID = job.getId();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testGetAllJobsByUserIdShouldPerformCorrect() throws Exception {
        this.mockMvc.perform(get("/api/jobs/all/{userId}", TEST_USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle", is(job.getJobTitle())))
                .andExpect(jsonPath("$[0].description", is(job.getDescription())))
                .andExpect(jsonPath("$[0].deliveryTime", is(job.getDeliveryTime())))
                .andExpect(jsonPath("$[0].workSphere.workSphere", is(job.getWorkSphere().getWorkSphere())))
                .andExpect(jsonPath("$[0].subSphere.subSphere", is(job.getSubSphere().getSubSphere())));
    }

    @Test
    public void testGetJobByIdShouldReturnCorrectResult() throws Exception {
        this.mockMvc.perform(get("/api/jobs/{jobId}", JOB_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle", is(job.getJobTitle())))
                .andExpect(jsonPath("$.description", is(job.getDescription())))
                .andExpect(jsonPath("$.deliveryTime", is(job.getDeliveryTime())))
                .andExpect(jsonPath("$.workSphere.workSphere", is(job.getWorkSphere().getWorkSphere())))
                .andExpect(jsonPath("$.subSphere.subSphere", is(job.getSubSphere().getSubSphere())));
    }

    @Test
    public void testGetJobByIdShouldReturn404StatusCodeWithInvalidId() throws Exception {
        this.mockMvc.perform(get("/api/jobs/{jobId}", "invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBuyJobShouldPerformCorrect() throws Exception {
        JobBuyBindingModel jobBuyBindingModel = new JobBuyBindingModel();
        jobBuyBindingModel.setJobId(JOB_ID);
        jobBuyBindingModel.setUserId(TEST_USER_ID);

        this.mockMvc.perform(post("/api/jobs/buy/{jobId}", JOB_ID)
                .content(asJsonString(jobBuyBindingModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddJobShouldPerformCorrect() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", VALID_FILE_NAME, VALID_FILE_TYPE, VALID_DATA);
        JobBindingModel jobBindingModel = this.modelMapper.map(job, JobBindingModel.class);
        jobBindingModel.getWorkSphere().setSubSphere(this.modelMapper.map(subSphere, SubSphereBindingModel.class));
        MockMultipartFile jsonFile = new MockMultipartFile("job", "job", "application/json", asJsonString(jobBindingModel).getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/api/jobs/add/{userId}", TEST_USER_ID)
                .file(multipartFile)
                .file(jsonFile)
                .contentType(MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetJobsBoughtByUserIdShouldReturnCorrectResult() throws Exception {
        job.setBoughtByUser(Sets.newHashSet(userProfileDetails));
        this.jobRepository.save(job);

        this.mockMvc.perform(get("/api/jobs/{userId}/bought", TEST_USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle", is(job.getJobTitle())))
                .andExpect(jsonPath("$[0].description", is(job.getDescription())))
                .andExpect(jsonPath("$[0].deliveryTime", is(job.getDeliveryTime())))
                .andExpect(jsonPath("$[0].workSphere.workSphere", is(job.getWorkSphere().getWorkSphere())))
                .andExpect(jsonPath("$[0].subSphere.subSphere", is(job.getSubSphere().getSubSphere())));
    }

    @Test
    public void testFindFiveJobsInGivenSubSphereShouldReturnCorrectResultWithOneJob() throws Exception {
        this.mockMvc.perform(get("/api/jobs/sub-sphere/{subSphereName}", subSphere.getSubSphere()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle", is(job.getJobTitle())))
                .andExpect(jsonPath("$[0].description", is(job.getDescription())))
                .andExpect(jsonPath("$[0].deliveryTime", is(job.getDeliveryTime())))
                .andExpect(jsonPath("$[0].workSphere.workSphere", is(job.getWorkSphere().getWorkSphere())))
                .andExpect(jsonPath("$[0].subSphere.subSphere", is(job.getSubSphere().getSubSphere())));
    }

    @Test
    public void testFindAllJobsInGivenSubSphereShouldReturnCorrectResult() throws Exception {
        this.mockMvc.perform(get("/api/jobs/sub-sphere/{subSphereId}/all", SUBSPHERE_ID)
                .queryParam("page", "0")
                .queryParam("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].jobTitle", is(job.getJobTitle())))
                .andExpect(jsonPath("$.content[0].description", is(job.getDescription())))
                .andExpect(jsonPath("$.content[0].deliveryTime", is(job.getDeliveryTime())))
                .andExpect(jsonPath("$.content[0].workSphere.workSphere", is(job.getWorkSphere().getWorkSphere())))
                .andExpect(jsonPath("$.content[0].subSphere.subSphere", is(job.getSubSphere().getSubSphere())));
    }

    @Test
    public void testDeleteJobByIdShouldPerformCorrect() throws Exception {
        this.mockMvc.perform(delete("/api/jobs/{jobId}", JOB_ID))
                .andExpect(status().isOk());

        Assertions.assertEquals(0, this.jobRepository.count());
    }
}
