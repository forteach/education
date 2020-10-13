package com.forteach.education.information.repository;

import com.forteach.education.information.domain.FriendlyLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendlyLinkDao extends JpaRepository<FriendlyLink, String>, JpaSpecificationExecutor<FriendlyLink> {


}
