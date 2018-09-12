package com.izhuixin.authsample.repository;

import com.izhuixin.authsample.entity.CustomInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:14:52
 * ProjectName:Mirco-Service-Skeleton
 */
@Repository
public interface CustomInfoRepository extends JpaRepository<CustomInfoEntity,Integer> {

    @Query(value = "select * from rsps_custom_info  where custom_id = ?1 or tel = ?1", nativeQuery = true)
    CustomInfoEntity findByCustomid(String customid);


}
