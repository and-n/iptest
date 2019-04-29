package ru.homeand.iptest.service;

import java.util.Objects;

public class IpStatistic {

    private final String ip;
    private long count;

    public IpStatistic(IpEntry ipEntry) {
        this.ip = ipEntry.getIp();
    }

    public void increaseCount() {
        count++;
    }

    public String getIp() {
        return ip;
    }

    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpStatistic that = (IpStatistic) o;
        return Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
