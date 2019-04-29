package ru.homeand.iptest.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IpRepository extends MongoRepository<IpEntry, String> {

	IpEntry findByIp(String ip);

	List<IpEntry> findAllByIp(String ip);

}
