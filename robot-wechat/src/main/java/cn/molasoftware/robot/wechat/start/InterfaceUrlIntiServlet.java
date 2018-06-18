package cn.molasoftware.robot.wechat.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * ClassName: InterfaceUrlIntiServlet
 * 
 * @Description: 项目启动初始化servlet
 * @author dapengniao
 * @date 2016�?3�?10�? 下午4:08:43
 */
public class InterfaceUrlIntiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		InterfaceUrlInti.init();
	}

}
