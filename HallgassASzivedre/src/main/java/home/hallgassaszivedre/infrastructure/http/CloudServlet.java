package home.hallgassaszivedre.infrastructure.http;

import home.hallgassaszivedre.app.service.CloudAppService;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.infrastructure.acl.DataConverter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

@SuppressWarnings("serial")
//@Component("cloudServlet")
public class CloudServlet extends HttpServlet implements HttpRequestHandler {
	
    @Autowired
	private CloudAppService cloudAppService;
    @Autowired
	private DataConverter dataConverter;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    
	    String json = "{\"mess\":\"null again\"}";
	    if (cloudAppService != null) {
	        List<Puff> puffs = cloudAppService.getPuffs();
	        json = dataConverter.convert(puffs);
	    }
		
		resp.setContentType("text/json");
		resp.getWriter().println(json);
	}

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    
    public void setCloudAppService(CloudAppService cloudAppService) {
        this.cloudAppService = cloudAppService;
    }
    public void setDataConverter(DataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }
}