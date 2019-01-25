package com.companyxxx.projectname.repository;

import com.companyxxx.projectname.domain.s.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description:
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
