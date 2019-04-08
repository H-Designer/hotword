package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Dao.InfoDaoImpl;
import Model.Info;
import oper.GetInfo;

/**
 * Servlet implementation class GetInfoOnlineServlet
 */
@WebServlet("/GetInfoOnlineServlet")
public class GetInfoOnlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoOnlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		System.out.println(name);
		GetInfo.gi(name);
		JSONObject jsonArray = GetInfo.jsonO;
		Info info = new Info();
		info.setName(name);
		info.setSum(1);
		info.setInformation(jsonArray.getString("information"));
		info.setNews(jsonArray.getString("news"));
		info.setNewsadd(jsonArray.getString("newsadd"));
		info.setType("（大数据）");
		info.setTitle("");
		info.setWebadd("");
		//out.write(jsonArray.toString());
		InfoDaoImpl.add(info);
		PrintWriter out = response.getWriter();
		String flag = "true";
		JSONArray array = new JSONArray();
		array.add(flag);
		out.write(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
