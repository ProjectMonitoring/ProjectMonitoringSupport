import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

public class Controller {

	public static void main(String[] args) throws Exception {

		Server server = new Server(8005);
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/projMonitoringdb");
		
		// config
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		// Mappings
		ctx.addServlet("servlets.ServletHome", "/home"); 	
		ctx.addServlet("servlets.ServletAddNewStudent", "/addnew");
		
		// API Route Mappings 
		ctx.addServlet("servlets.ServletApiStudent", "/apiStudent");
		ctx.addServlet("servlets.ServletApiTutor", "/apiTutor");
		ctx.addServlet("servlets.ServletApiProject", "/apiProject");
		ctx.addServlet("servlets.ServletApiThread", "/apiThread");
		ctx.addServlet("servlets.ServletApiPost", "/apiPost");
		ctx.addServlet("servlets.ServletApiTeam", "/apiTeam");
		ctx.addServlet("servlets.ServletApiStudentTeam", "/apiStudentTeam");
		ctx.addServlet("servlets.ServletApiTutorProject", "/apiTutorProject");
		ctx.addServlet("servlets.ServletApiTutorStudent", "/apiTutorStudent");
		
		// Setting the handler and starting the Server
		server.setHandler(ctx);
		server.start();
		server.join();
	}
}

