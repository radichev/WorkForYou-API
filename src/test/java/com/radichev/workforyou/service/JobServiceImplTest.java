package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.*;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.SubSphereBindingModel;
import com.radichev.workforyou.model.bindingModels.WorkSphereBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobScheduledTaskDto;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import com.radichev.workforyou.repository.JobRepository;
import com.radichev.workforyou.service.serviceImpl.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest {

    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private UserProfileDetailsService userProfileDetailsService;

    @Mock
    private WorkSphereService workSphereService;

    @Mock
    private SubSphereService subSphereService;

    @Mock
    private FileStoreService fileStoreService;

    private ModelMapper modelMapper = new ModelMapper();

    private UserProfileDetails userProfileDetails;
    private Job job;
    private WorkSphere workSphere;
    private SubSphere subSphere;
    private JobBindingModel jobBindingModel;

    private final String VALID_FILE_NAME = "validFileName";
    private final String VALID_FILE_TYPE = IMAGE_JPEG.getMimeType();
    private final byte[] VALID_DATA = new byte[]{11, 11};

    @BeforeEach
    public void setUp() {
        this.jobService = new JobServiceImpl(jobRepository,
                                             modelMapper,
                                             userProfileDetailsService,
                                             workSphereService,
                                             subSphereService,
                                             fileStoreService);

        userProfileDetails = new UserProfileDetails();
        userProfileDetails.setId("testId");
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");
        userProfileDetails.setCountry(new Country("Bulgaria"));
        userProfileDetails.setUser(new User());

        workSphere = new WorkSphere();
        workSphere.setWorkSphere("Programming");

        subSphere = new SubSphere();
        subSphere.setSubSphere("Web Development");

        job = new Job();
        job.setJobTitle("Java Developer");
        job.setDescription("Very good java developer here!");
        job.setDeliveryTime(20);
        job.setPrice(BigDecimal.valueOf(20));
        job.setJobPicture("job picture");
        job.setWorkSphere(workSphere);
        job.setSubSphere(subSphere);
        job.setUserProfileDetails(userProfileDetails);
        job.setCreatedDate(LocalDate.now());

        jobBindingModel = new JobBindingModel();
        jobBindingModel.setJobTitle("C# Developer");
        jobBindingModel.setDescription("Very good C# developer here!");
        jobBindingModel.setDeliveryTime(32);
        jobBindingModel.setPrice(BigDecimal.valueOf(56));


    }

    @Test
    public void testAddJobShouldReturnCorrectResult() throws IOException {
        when(this.userProfileDetailsService.findUserProfileDetailsById("testId"))
                .thenReturn(userProfileDetails);

        when(this.jobRepository.save(Mockito.any(Job.class)))
                .thenReturn(job);

        JobBindingModel jobBindingModel = this.modelMapper.map(job, JobBindingModel.class);
        jobBindingModel.setWorkSphere(this.modelMapper.map(workSphere, WorkSphereBindingModel.class));
        jobBindingModel.getWorkSphere().setSubSphere(this.modelMapper.map(subSphere, SubSphereBindingModel.class));

        MultipartFile multipartFile = new MockMultipartFile("picture", VALID_FILE_NAME, VALID_FILE_TYPE, VALID_DATA);

        when(this.workSphereService.findWorkSphereById(jobBindingModel.getWorkSphere().getId()))
                .thenReturn(workSphere);

        when(this.subSphereService.findSubSphereById(jobBindingModel.getWorkSphere().getSubSphere().getId()))
                .thenReturn(subSphere);

        JobViewModel testJob = this.jobService.addJob(jobBindingModel, "testId", multipartFile);

        Assertions.assertEquals(job.getJobTitle(), testJob.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJob.getDescription());
        Assertions.assertEquals(job.getDeliveryTime(), testJob.getDeliveryTime());
        Assertions.assertEquals(job.getPrice(), testJob.getPrice());
        Assertions.assertEquals(job.getWorkSphere().getWorkSphere(), testJob.getWorkSphere().getWorkSphere());
        Assertions.assertEquals(job.getSubSphere().getSubSphere(), testJob.getSubSphere().getSubSphere());
        Assertions.assertEquals(job.getUserProfileDetails().getUser().getUsername(), testJob.getUserProfileDetails().getUsername());
        Assertions.assertEquals(job.getUserProfileDetails().getCountry().getCountry(), testJob.getUserProfileDetails().getCountry());
    }

    @Test
    public void testFindAllJobsByUserIdShouldReturnCorrectResult() {
        when(this.jobRepository.findAllByUserId("testId"))
                .thenReturn(List.of(job));

        List<JobViewModel> testJobCollection = this.jobService.findAllJobsByUserId("testId");

        Assertions.assertEquals(1, testJobCollection.size());
        JobViewModel testJobViewModel = testJobCollection.get(0);

        Assertions.assertEquals(job.getJobTitle(), testJobViewModel.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJobViewModel.getDescription());
        Assertions.assertEquals(job.getPrice(), testJobViewModel.getPrice());
        Assertions.assertEquals(job.getDeliveryTime(), testJobViewModel.getDeliveryTime());
        Assertions.assertEquals(job.getWorkSphere().getWorkSphere(), testJobViewModel.getWorkSphere().getWorkSphere());
        Assertions.assertEquals(job.getSubSphere().getSubSphere(), testJobViewModel.getSubSphere().getSubSphere());
        Assertions.assertEquals(job.getUserProfileDetails().getUser().getUsername(), testJobViewModel.getUserProfileDetails().getUsername());
        Assertions.assertEquals(job.getUserProfileDetails().getCountry().getCountry(), testJobViewModel.getUserProfileDetails().getCountry());
    }

    @Test
    public void testFindJobByIdShouldReturnCorrectResult() {
        when(this.jobRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(job));

        Job testJob = this.jobService.findJobById("testId");

        Assertions.assertEquals(job.getJobTitle(), testJob.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJob.getDescription());
        Assertions.assertEquals(job.getDeliveryTime(), testJob.getDeliveryTime());
        Assertions.assertEquals(job.getPrice(), testJob.getPrice());
        Assertions.assertEquals(job.getWorkSphere().getWorkSphere(), testJob.getWorkSphere().getWorkSphere());
        Assertions.assertEquals(job.getSubSphere().getSubSphere(), testJob.getSubSphere().getSubSphere());
    }

    @Test
    public void testFindJobByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.jobService.findJobById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Job not found with testId id.");
    }

    @Test
    public void testFindFiveJobsInGivenSubSphereShouldReturnCorrectResult() {
        when(this.jobRepository.findFiveJobsBySubSphere("testSubsphere", PageRequest.of(0, 5)))
                .thenReturn(List.of(job, job, job, job, job));

        List<JobViewModel> testJobCollection = this.jobService
                .findFiveJobsInGivenSubSphere("testSubsphere", PageRequest.of(0, 5));

        Assertions.assertEquals(5, testJobCollection.size());
        JobViewModel testJobViewModel = testJobCollection.get(0);
        JobViewModel testJobViewModel2 = testJobCollection.get(3);


        Assertions.assertEquals(job.getJobTitle(), testJobViewModel.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJobViewModel.getDescription());
        Assertions.assertEquals(job.getPrice(), testJobViewModel.getPrice());
        Assertions.assertEquals(job.getDeliveryTime(), testJobViewModel.getDeliveryTime());

        Assertions.assertEquals(job.getJobTitle(), testJobViewModel2.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJobViewModel2.getDescription());
        Assertions.assertEquals(job.getPrice(), testJobViewModel2.getPrice());
        Assertions.assertEquals(job.getDeliveryTime(), testJobViewModel2.getDeliveryTime());;
    }

    @Test
    public void testFindAllJobsBySubsphereIdShouldReturnCorrectResult() {
        Page<Job> jobs = new PageImpl<>(List.of(job));
        when(this.jobRepository.findAllJobsBySubSphereId("testSubsphere", PageRequest.of(0, 5)))
                .thenReturn(jobs);

        Page<JobViewModel> testJobCollection = this.jobService
                .findAllJobsBySubSphereId("testSubsphere", PageRequest.of(0, 5));

        Assertions.assertEquals(1, testJobCollection.getTotalElements());
        List<JobViewModel> jobViewModelStream = testJobCollection.get().collect(Collectors.toList());
        JobViewModel testJob = jobViewModelStream.get(0);

        Assertions.assertEquals(job.getJobTitle(), testJob.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJob.getDescription());
        Assertions.assertEquals(job.getPrice(), testJob.getPrice());
        Assertions.assertEquals(job.getDeliveryTime(), testJob.getDeliveryTime());
    }

    @Test
    public void testFindJobsBoughtByUserIdShouldReturnCorrectResult() {
        when(this.jobRepository.findJobsBoughtByUserId("testId"))
                .thenReturn(List.of(job));

        List<JobViewModel> testJobCollection = this.jobService.findJobsBoughtByUserId("testId");
        Assertions.assertEquals(1, testJobCollection.size());

        JobViewModel testJob = testJobCollection.get(0);

        Assertions.assertEquals(job.getJobTitle(), testJob.getJobTitle());
        Assertions.assertEquals(job.getDescription(), testJob.getDescription());
        Assertions.assertEquals(job.getPrice(), testJob.getPrice());
        Assertions.assertEquals(job.getDeliveryTime(), testJob.getDeliveryTime());
    }

    @Test
    public void testFindAllJobsShouldReturnCorrectResult() {
        when(this.jobRepository.findAll())
                .thenReturn(List.of(job));

        List<JobScheduledTaskDto> testJobCollection = this.jobService.findAllJobs();

        Assertions.assertEquals(1, testJobCollection.size());

        JobScheduledTaskDto testJob = testJobCollection.get(0);

        Assertions.assertEquals(job.getJobTitle(), testJob.getJobTitle());
        Assertions.assertEquals(job.getCreatedDate(), testJob.getCreatedDate());
    }

    @Test
    public void testUploadJobImageShouldThrowIllegalStateExceptionWithWrongFileType() {
        MultipartFile multipartFile = new MockMultipartFile("picture", VALID_FILE_NAME, "invalidType", VALID_DATA);

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            this.jobService.uploadJobImage("testId", "testId", null, multipartFile);
        });

        Assertions.assertEquals(exception.getMessage(), "File must be an image");
    }

    @Test
    public void testUploadJobImageShouldThrowIllegalStateExceptionWithEmptyFile() {
        byte[] emptyFile = null;
        MultipartFile multipartFile = new MockMultipartFile("picture", VALID_FILE_NAME, "invalidType", emptyFile);

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            this.jobService.uploadJobImage("testId", "testId", null, multipartFile);
        });

        Assertions.assertEquals(exception.getMessage(), "Cannot upload empty file");
    }
}
