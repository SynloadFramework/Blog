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

public class Categories extends Response{
	public List<Object> categories = new ArrayList<Object>();
	public Categories(WSHandler user, List<String> templateCache, String categoryPartial){
		this.setTemplateId("catPart");
		try {
			List<Category> cats;
			if(!categoryPartial.isEmpty()){
				cats = Category._find(Category.class, "`title` LIKE ?", "%"+categoryPartial+"%").exec(Category.class);
			}else{
				cats = Category._find(Category.class, "").exec(Category.class);
			}
			for(Category c: cats){
				categories.add(new Object[]{c,c._related(Entry.class).count()});
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		this.setTemplate(this.getTemplate("./elements/categories.html"));
		this.setParent("#body");
		Request r = new Request("get","categories");
			r.getData().put("partial", categoryPartial);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		this.setPageId("catPart");
		this.setAction("cabot");
		this.setPageTitle(" Blog Categories");
	}
}