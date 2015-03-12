package blog.synload.com.elements;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import blog.synload.com.sql.Category;
import blog.synload.com.sql.Entry;
import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.ws.WSHandler;

public class Index extends Response{
	public List<Object> entries = new ArrayList<Object>();
	public List<Category> categories = new ArrayList<Category>();
	public Index(WSHandler user, List<String> templateCache){
		try {
			List<Entry> etmp = Entry._find(Entry.class, "`publicPost`=?", 1).orderBy("`date` DESC").exec(Entry.class);
			for(Entry e: etmp){
				entries.add(new Object[]{
					e,
					e._related(Category.class).exec(Category.class)
				});
			}
			categories = Category._find(Category.class, "").exec(Category.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		this.setTemplateId("IDX");
		this.setTemplate(this.getTemplate("./elements/index.html"));
		this.setParent("#body");
		Request r = new Request("get","index");
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		this.setPageId("Index");
		this.setAction("cabot");
		this.setPageTitle(" Blog Index");
	}
}
