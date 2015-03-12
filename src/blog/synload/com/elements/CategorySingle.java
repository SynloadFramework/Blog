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

public class CategorySingle extends Response{
	public Category category;
	public List<Object> entries = new ArrayList<Object>();
	public CategorySingle(WSHandler user, List<String> templateCache, String categoryId){
		this.setTemplateId("catSingle");
		try {
			category = Category._find(Category.class, "`id`=?", categoryId).limit("1").exec(Category.class).get(0);
			List<Entry> etmps = category._related(Entry.class).orderBy("`date` DESC").exec(Entry.class);
			for(Entry e: etmps){
				entries.add(new Object[]{
					e,
					e._related(Category.class).exec(Category.class)
				});
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		this.setTemplate(this.getTemplate("./elements/category.html"));
		this.setParent("#body");
		Request r = new Request("get","category");
			r.getData().put("categoryId", categoryId);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		this.setPageId("catSingle");
		this.setAction("cabot");
		this.setPageTitle(" Blog Category ["+category.getTitle()+"]");
	}
}