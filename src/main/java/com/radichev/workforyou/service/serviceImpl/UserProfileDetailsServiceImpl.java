package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Language;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserProfileDetails createUserProfileDetails(RegisterBindingModel registerBindingModel) {
        UserProfileDetails userProfileDetails = this.modelMapper.map(registerBindingModel, UserProfileDetails.class);

        return this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    @Transactional
    public void editUserProfileDetails(UserProfileDetailsBindingModel userProfileDetailsBindingModel, String id) {
        UserProfileDetails edited = this.modelMapper.map(userProfileDetailsBindingModel, UserProfileDetails.class);

        UserProfileDetails userProfileDetails = this.getUserProfileDetails(id);


        userProfileDetails.setFirstName(edited.getFirstName());
        userProfileDetails.setLastName(edited.getLastName());
        userProfileDetails.setDescription(edited.getDescription());
        userProfileDetails.setPersonalWebsite(edited.getPersonalWebsite());
        userProfileDetails.setProfilePicture(edited.getProfilePicture());
        userProfileDetails.setCountry(edited.getCountry());
        userProfileDetails.setEmail(edited.getEmail());
        userProfileDetails.setLanguages(edited.getLanguages());
        userProfileDetails.setWorkSpheres(edited.getWorkSpheres());
        userProfileDetails.setSkills(edited.getSkills());
        userProfileDetails.setEducations(edited.getEducations());
        userProfileDetails.setCertificates(edited.getCertificates());

        this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    public UserProfileDetails getUserProfileDetails(String id) {
        return this.userRepository.findUserProfileDetails(id)
                .orElseThrow(() ->
                        new InvalidEntityException(String.format("UserProfileDetails not found with %s id.", id))
                );
    }
}