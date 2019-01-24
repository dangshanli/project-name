package com.companyxxx.projectname.repository;

import com.companyxxx.projectname.domain.p.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description: respository 样例展示
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
