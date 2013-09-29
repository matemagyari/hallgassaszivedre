package home.hallgassaszivedre.infrastructure.http;

import home.hallgassaszivedre.app.service.CloudAppService;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.infrastructure.acl.DataConverter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.web.HttpRequestHandler;

@Named("cloudServlet")
public class CloudServlet implements HttpRequestHandler {

	@Inject
	private CloudAppService cloudAppService;
	@Inject
	private DataConverter dataConverter;

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String json = "{ \"status\" : 0 }";
		resp.setContentType("text/json");

		UIAction uiAction = UIAction.valueOf(req.getParameter("action"));

		try {
			if (UIAction.get_all == uiAction) {
				List<Puff> puffs = cloudAppService.getPuffs();
				json = dataConverter.toJSON(puffs);
			} else if (UIAction.create == uiAction) {
				Puff puff = dataConverter.fromJSON(getPostBody(req));
				cloudAppService.createPuff(puff);
				json = String.format("{ \"id\" : %d }", puff.getId());
			} else if (UIAction.update == uiAction) {
				Puff puff = dataConverter.fromJSON(getPostBody(req));
				cloudAppService.updatePuff(puff);
			} else if (UIAction.delete == uiAction) {
				String body = getPostBody(req);
				JSONObject jsonObject = new JSONObject(body);
				cloudAppService.deletePuff(jsonObject.getLong("id"));
			}
			resp.setStatus(200);
		} catch (Exception ex) {
			resp.setStatus(500);
			json = ex.getMessage();
		}

		resp.getWriter().println(json);
	}

	private static String getPostBody(HttpServletRequest request) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(request.getInputStream(), writer, "utf-8");
			String body = writer.toString();
			if (StringUtils.isBlank(body)) {
				throw new RuntimeException("Post body is empty");
			}
			return body;
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