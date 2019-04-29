package ru.homeand.iptest.service;

import java.util.List;

public interface IpService {

    void save(String ip);

    List<IpEntry> getAllIpEntriesByIpAddress(String ipAdress);

    List<IpStatistic> getAllIpStatistics();
}
