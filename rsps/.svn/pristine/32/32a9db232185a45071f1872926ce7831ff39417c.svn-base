package com.izhuixin.authsample.repository;

import com.izhuixin.authsample.entity.SysPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:39
 * ProjectName:Mirco-Service-Skeleton
 */
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity,Integer> {
    @Query(value = "select p.* from sys_permission p,sys_permission_user pu where p.permission_id=pu.permission_id and pu.user_id=?1",nativeQuery = true)
    List<SysPermissionEntity> getPermissionsByUserId(String rpId);
}
