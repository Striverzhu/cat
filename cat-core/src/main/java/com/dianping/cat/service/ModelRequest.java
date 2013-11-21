package com.dianping.cat.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelRequest {

	private String m_domain;

	private long m_startTime;

	private ModelPeriod m_period;

	private Map<String, String> m_properties;

	public ModelRequest(String domain, long startTime) {
		m_domain = domain;
		m_startTime = startTime;
		m_period = ModelPeriod.getByTime(startTime);
	}

	public String getDomain() {
		return m_domain;
	}

	public ModelPeriod getPeriod() {
		return m_period;
	}

	public Map<String, String> getProperties() {
		if (m_properties == null) {
			return Collections.emptyMap();
		} else {
			return m_properties;
		}
	}

	public String getProperty(String name) {
		return getProperty(name, null);
	}

	public String getProperty(String name, String defaultValue) {
		if (m_properties == null) {
			return defaultValue;
		} else if (m_properties.containsKey(name)) {
			return m_properties.get(name);
		} else {
			return defaultValue;
		}
	}

	public long getStartTime() {
		if (m_startTime >= 0) {
			return m_startTime;
		} else {
			return Long.parseLong(m_properties.get("date"));
		}
	}

	public ModelRequest setProperty(String name, String value) {
		if (m_properties == null) {
			m_properties = new HashMap<String, String>();
		}

		m_properties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return String.format("ModelRequest[domain=%s, period=%s, properties=%s]", m_domain, m_period, m_properties);
	}
}
