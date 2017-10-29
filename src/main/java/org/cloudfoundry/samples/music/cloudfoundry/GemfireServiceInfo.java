package org.cloudfoundry.samples.music.cloudfoundry;

import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

@ServiceLabel("gemfire")
public class GemfireServiceInfo extends BaseServiceInfo {

	private final Log logger = LogFactory.getLog(this.getClass());

	private List<URI> locators;

	public GemfireServiceInfo(String id, List<URI> locators) {
		super(id);
		this.logger.info("id: " + id + " locators: " + locators);
		this.locators = locators;
	}

	@ServiceProperty(category = "locators")
	public List<URI> getLocators() {
		this.logger.info("getLocators: " + locators);
		return locators;
	}
}
