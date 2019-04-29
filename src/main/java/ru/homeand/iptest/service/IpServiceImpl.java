package ru.homeand.iptest.service;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IpServiceImpl implements IpService {

    private static final Logger log = LoggerFactory.getLogger(IpService.class);

    @Autowired
    private IpRepository ipRepository;

    public IpServiceImpl() {
    }

    @Override
    public List<IpEntry> getAllIpEntriesByIpAddress(@NonNull String ipAddress) {
        log.info("find all for " + ipAddress);
        return ipRepository.findAllByIp(ipAddress);
    }

    @Override
    public void save(@NonNull String ip) {
        ipRepository.save(new IpEntry(ip, DateTime.now()));
        log.info("save " + ip);
    }

    @Override
    public List<IpStatistic> getAllIpStatistics() {
        final List<IpEntry> res = ipRepository.findAll();
        final Map<String, IpStatistic> ipStatisticMap = new HashMap<>();
        res.forEach(ipEntry -> {
            IpStatistic ipStatistic = new IpStatistic(ipEntry);
            if (ipStatisticMap.containsKey(ipEntry.getIp())) {
                ipStatistic = ipStatisticMap.get(ipEntry.getIp());
            }
            ipStatistic.increaseCount();
            ipStatisticMap.put(ipEntry.getIp(), ipStatistic);
        });
        return new ArrayList<>(ipStatisticMap.values());
    }
}
