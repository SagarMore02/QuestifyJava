package com.questify.questify.facade;

import com.questify.questify.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationFacade {

  private final ApplicationRepository applicationRepository;

  @Autowired
  public ApplicationFacade(ApplicationRepository applicationRepository) {
    this.applicationRepository = applicationRepository;
  }

  public long countApplicationsByOrganizerId(Long organizerId) {
    return applicationRepository.countApplicationsByOrganizer(organizerId);
  }

  public long countPendingApplications(Long organizerId) {
    return applicationRepository.countPendingApplications(organizerId);
  }

}
