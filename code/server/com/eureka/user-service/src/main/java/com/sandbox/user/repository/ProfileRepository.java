package com.sandbox.user.repository;

import com.sandbox.user.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by arthur on 15/11/14.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
