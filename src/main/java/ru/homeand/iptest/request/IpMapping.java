package ru.homeand.iptest.request;

import org.joda.time.DateTimeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.homeand.iptest.service.IpEntry;
import ru.homeand.iptest.service.IpService;
import ru.homeand.iptest.service.IpStatistic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class IpMapping {

    private static final Logger log = LoggerFactory.getLogger(IpMapping.class);
    private static final DateTimeComparator DATE_TIME_COMPARATOR = DateTimeComparator.getInstance();

    @Autowired
    IpService ipService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get(HttpServletRequest request) {
        log.debug("new request");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAdress " + ipAddress);
        if (!ipAddress.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            return "Hello!";
        }

        final List<IpEntry> entries = ipService.getAllIpEntriesByIpAddress(ipAddress);
        ipService.save(ipAddress);
        if (entries.isEmpty()) {
            return "Nice to see you!";
        }
        final IpEntry lastDateTime = entries.stream().max((o1, o2) -> DATE_TIME_COMPARATOR.compare(o1.getTime(), o2.getTime())).get();
        return "Hello again! You came here " + entries.size() + " times and the last was at " + lastDateTime.getTime().toDate().toString();
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String getStatistic() {
        log.debug("get stats");
        final List<IpStatistic> statisticList = ipService.getAllIpStatistics();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<table border=\"1\">");
        for (IpStatistic statistic : statisticList) {
            stringBuilder.append("<tr><td>")
                    .append(statistic.getIp())
                    .append("</td><td>")
                    .append(statistic.getCount())
                    .append("</td></tr>");
        }
        return stringBuilder.toString();
    }


}
