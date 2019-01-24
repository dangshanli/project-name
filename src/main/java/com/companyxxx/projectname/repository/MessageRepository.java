package com.companyxxx.projectname.repository;

import com.companyxxx.projectname.domain.s.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description:
 */
public interface MessageRepository extends JpaRepository<Message,Long> {
}
