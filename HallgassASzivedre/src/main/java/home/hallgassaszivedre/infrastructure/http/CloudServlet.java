package home.hallgassaszivedre.infrastructure.http;

import home.hallgassaszivedre.app.service.CloudAppService;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.infrastructure.acl.DataConverter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

@Component("cloudServlet")
public class CloudServlet implements HttpRequestHandler {

    @Autowired
    private CloudAppService cloudAppService;
    @Autowired
    private DataConverter dataConverter;

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = null;
        
        UIAction uiAction = UIAction.valueOf(req.getParameter("action"));

        if (UIAction.get_all == uiAction) {
            List<Puff> puffs = cloudAppService.getPuffs();
            json = dataConverter.convertToDTO(puffs);
        } else if (UIAction.create == uiAction) {
            Puff puff = dataConverter.convertFromDTO(getPostBody(req));
            cloudAppService.createPuff(puff);
        } else if (UIAction.update == uiAction) {
            Puff puff = dataConverter.convertFromDTO(getPostBody(req));
            cloudAppService.updatePuff(puff);
        } 
        
        resp.setContentType("text/json");
        resp.getWriter().println(json);
    }

    private static String getPostBody(HttpServletRequest request) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(request.getInputStream(), writer, "utf-8");
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCloudAppService(CloudAppService cloudAppService) {
        this.cloudAppService = cloudAppService;
    }

    public void setDataConverter(DataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }
}