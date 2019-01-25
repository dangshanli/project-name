package com.companyxxx.projectname.repository;

import com.companyxxx.projectname.domain.p.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description: respository 样例展示
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
