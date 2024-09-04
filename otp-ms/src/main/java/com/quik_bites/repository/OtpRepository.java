package com.quik_bites.repository;

import com.quik_bites.entity.OtpRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OtpRepository extends JpaRepository<OtpRecord , Long> {

    @Query("SELECT o FROM OtpRecord o WHERE o.mobileNumber = :mobileNumber  ORDER BY o.createdAt DESC LIMIT 1")

    OtpRecord findLatestOtpByMobileNumber(@Param("mobileNumber") String mobileNumber);

}
