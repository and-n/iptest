package ru.homeand.iptest.service;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@DataMongoTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "ru.homeand.iptest.service")
public class IpServiceTest {

    @Autowired
    IpService ipService;

    @BeforeAll
    static void setUp() throws Exception {
    }

    @Test
    public void test_save() {

        final IpEntry ipEntry = new IpEntry("192.168.0.0", DateTime.now());
        ipService.save(ipEntry.getIp());

        List<IpEntry> res = ipService.getAllIpEntriesByIpAddress(ipEntry.getIp());
        System.out.println(res.size());
        Assertions.assertTrue(res.size() == 1);
        Assertions.assertEquals(ipEntry.getIp(), res.get(0).getIp());
        Assertions.assertTrue(ipEntry.getTime().isBefore(res.get(0).getTime()));
    }

}