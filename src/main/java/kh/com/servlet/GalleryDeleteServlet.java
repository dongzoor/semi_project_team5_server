package kh.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kh.com.common.Common;
import kh.com.dao.GalleryDAO;

/**
 * Servlet implementation class GalleryDeleteServlet
 */
@WebServlet("/GalleryDeleteServlet")
public class GalleryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common.corsResSet(response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Common.corsResSet(response);

		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		String getId = (String)jsonObj.get("gal_id");
		int intId = Integer.parseInt(getId);
		GalleryDAO dao = new GalleryDAO();
		boolean rstDelete = dao.galleryDelete(intId);
		
		PrintWriter out = response.getWriter();
		JSONObject resJson = new JSONObject();
		
		if(rstDelete) resJson.put("result", "OK");
		else resJson.put("result", "NOK");
		out.print(resJson);
		
	}


}
