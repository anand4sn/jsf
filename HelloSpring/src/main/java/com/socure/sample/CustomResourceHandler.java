package com.socure.sample;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;

/**
* Custom JSF ResourceHandler.
*
* This handler bridges between Spring MVC and JSF managed resources. The
* handler takes care of the case when a JSF facelet is used as a view by a
* Spring MVC Controller and the view uses components like h:outputScript and
* h:outputStylesheet by correctly pointing the resource URLs generated to the
* JSF resource handler.
*
* The reason this custom handler wrapper is needed is because the JSF internal
* logic assumes that the request URL for the current page/view is a JSF url. If
* it is a Spring MVC request, JSF will create URLs that incorrectly includes
* the Spring controller context.
*
* This handler will strip out the Spring context for the URL and add the ".jsf"
* suffix, so the resource request will be routed to the FacesServlet with a
* correct resource context (assuming the faces servlet is mapped to the *.jsf
* pattern).
*
*/
public class CustomResourceHandler extends ResourceHandlerWrapper {
	private ResourceHandler wrapped;

	public CustomResourceHandler(ResourceHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ResourceHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public Resource createResource(String resourceName, String libraryName) {
		return new CustomResource(super.createResource(resourceName,
				libraryName));
	}

	@Override
	public Resource createResource(String resourceName, String libraryName,
			String contentType) {
		return new CustomResource(super.createResource(resourceName,
				libraryName, contentType));
	}

}
