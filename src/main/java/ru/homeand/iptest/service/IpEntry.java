package ru.homeand.iptest.service;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "ips")
public class IpEntry {

	@Id
	private String id;

	@Indexed
	private String ip;
	private DateTime time;

	public IpEntry() {
	}

	public IpEntry(String ip, DateTime time) {
		this.ip = ip;
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public DateTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "IpEntry{" +
				"id='" + id + '\'' +
				", ip='" + ip + '\'' +
				", time=" + time +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		IpEntry ipEntry = (IpEntry) o;
		return Objects.equals(id, ipEntry.id) &&
				Objects.equals(ip, ipEntry.ip) &&
				Objects.equals(time, ipEntry.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ip, time);
	}
}
