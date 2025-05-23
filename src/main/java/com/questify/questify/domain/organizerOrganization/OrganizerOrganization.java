package com.questify.questify.domain.organizerOrganization;

import jakarta.persistence.Entity;

@Entity(name = "Organizer_Organization")
public class OrganizerOrganization {

  private long organizerId;
  private long organizationId;

}
