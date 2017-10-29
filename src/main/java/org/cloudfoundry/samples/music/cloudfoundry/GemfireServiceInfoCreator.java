package org.cloudfoundry.samples.music.cloudfoundry;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

// cf uups gemfire -p '{"uri":"locator://host.pcfdev.io:10334;locator://host.pcfdev.io:10335"}'

public class GemfireServiceInfoCreator extends CloudFoundryServiceInfoCreator<GemfireServiceInfo> {

	private final Log logger = LogFactory.getLog(this.getClass());

	public GemfireServiceInfoCreator() {
		super(new Tags("gemfire"));
	}

	@Override
	public boolean accept(Map<String, Object> serviceData) {
		Map<String, Object> credentials = getCredentials(serviceData);

		return credentials != null && ((String) serviceData.get("name")).startsWith("gemfire")
				&& ((String) serviceData.get("label")).compareTo("user-provided") == 0;
	}

	@Override
	public GemfireServiceInfo createServiceInfo(Map<String, Object> serviceData) {

		String id = (String) serviceData.get("name");

		Map<String, Object> credentials = getCredentials(serviceData);

		List<URI> locators = new ArrayList<URI>();

		URI uri;
		String uris = (String) credentials.get("uri");

		List<String> items = Arrays.asList(uris.split("\\s*;\\s*"));
		this.logger.info("LIST: " + items);

		for (String item : items) {
			try {
				uri = new URI(item);
				locators.add(uri);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new GemfireServiceInfo(id, locators);
	}
}