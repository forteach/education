package com.forteach.education.information.repository;

import com.forteach.education.information.domain.Article;
import com.forteach.education.information.domain.FriendlyLink;
import com.forteach.education.information.dto.IArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendlyLinkDao extends JpaRepository<FriendlyLink, String>, JpaSpecificationExecutor<FriendlyLink> {


}
