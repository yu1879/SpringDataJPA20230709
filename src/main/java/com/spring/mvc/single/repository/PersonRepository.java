package com.spring.mvc.single.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.spring.mvc.single.entity.Person;

/*
 * 方法命名規則
 * 1. 查詢方法以 find | read | get 開頭
 * 2. 涉及條件查詢時，條件的屬性（首字母大寫）用條件關鍵字（例如：And、Or）連結
 * */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> getByName(String name);

	List<Person> getByNameStartingWithAndIdGreaterThanEqual(String name, Long id);

	List<Person> getBIdIn(List<Long> ids);

	List<Person> getByBirthLessThen(Date birth);

	List<Person> getByBirthBetween(Date birthBegin, Date birthEnd);

	@Query(nativeQuery = true, value = "select id, name, password, birth from person where id >= 1? and id <= 2? ")
	List<Person> findPeron(Long beginId, Long endId);

}
