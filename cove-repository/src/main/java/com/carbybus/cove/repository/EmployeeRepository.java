package com.carbybus.cove.repository;

import com.carbybus.cove.domain.entity.company.Employee;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 员工仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

}
