package com.photoMart.photographerservice.repository;


import com.photoMart.photographerservice.entity.Photographer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface PhotographerRepo extends JpaRepository<Photographer,Long> {
    @Query("select p from Photographer p where p.isEnable = true")
    List<Photographer> getPhotographersInLimite(Pageable pageable);

    @Query("select p from Photographer p where p.isEnable = true")
    List<Photographer> findPhotographerInLimit();
    @Transactional
    @Modifying
    @Query("""
            update Photographer p set p.photographerMobileNo = ?1, p.studioName = ?2, p.studioEmail = ?3, p.description = ?4, p.profilePicLink = ?5, p.whatsAppNumber = ?6, p.contactEmail = ?7, p.faceBookProfile = ?8,
            p.address = ?9 where p.photographerId = ?10""")
    int updatePhotographerByIdEquals(@NonNull String photographerMobileNo, @NonNull String studioName,
                                     @NonNull String studioEmail, @NonNull String description,
                                     @NonNull String profilePicLink, @NonNull String whatsAppNumber,
                                     @NonNull String contactEmail, @NonNull String faceBookProfile,@NonNull String address,
                                     @NonNull long photographerId);
    List<Photographer> findByIsEnableTrue();
    Optional<Photographer> findByPhotographerEmailEqualsIgnoreCase(@NonNull String photographerEmail);
    boolean existsByPhotographerEmailEqualsIgnoreCase(@NonNull String photographerEmail);

}
