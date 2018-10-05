package com.ride808.webcrawler.repository;

import com.ride808.webcrawler.entity.SiteMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SiteMapRepository Class for retrieving SiteMap Objects from Embedded Storage (JPA)
 *
 */

@Repository
public interface SiteMapRepository extends JpaRepository<SiteMap, Long> {

}
