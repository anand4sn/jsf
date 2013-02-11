package com.socure.sample;

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.sun.faces.util.Util;

public class CustomResource extends ResourceWrapper {
	private Resource wrapped;

	CustomResource(Resource wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public Resource getWrapped() {
		return this.wrapped;
	}

	@Override
	public String getRequestPath() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String path = req.getRequestURL().toString();
		//String path = super.getRequestPath();
		System.out.println("path = " + path);

		FacesContext context = FacesContext.getCurrentInstance();
		String facesServletMapping = Util.getFacesMapping(context);
		System.out.println("facesServletMapping = " + facesServletMapping);

		// if prefix-mapped, this is a resource that is requested from a
		// faces page rendered as a view to a Spring MVC controller.
		// facesServletMapping will, in fact, be the Spring mapping
		if (Util.isPrefixMapped(facesServletMapping)) {
			// remove the Spring mapping
			path = path.replaceFirst("(" + facesServletMapping + ")/", "/");

			// append .jsf to route this URL to the FacesServlet
			/*System.out.println("ResourseName = " + wrapped.getResourceName());
			path = path.replace(wrapped.getResourceName(),
					wrapped.getResourceName() + ".jsf");*/
			path = path + ".jsf";
		}

		System.out.println("return path = " + path);
		return path;
	}

}
