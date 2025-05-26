package com.questify.questify.domain.organizerOrganization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Organizer_Organization")
public class OrganizerOrganization {

  @Id
  @Column(name = "organizer_organizationID", nullable = false)
  private long id;
  private long organizerId;
  private long organizationId;

}
