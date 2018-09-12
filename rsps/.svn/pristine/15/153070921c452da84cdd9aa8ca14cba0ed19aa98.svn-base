package com.izhuixin.authsample.repository;

import com.izhuixin.authsample.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:14:52
 * ProjectName:Mirco-Service-Skeleton
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity,Integer> {
    SysUserEntity findByUsername(String username);
}
