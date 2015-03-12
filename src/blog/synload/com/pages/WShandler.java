package blog.synload.com.pages;

import blog.synload.com.elements.Categories;
import blog.synload.com.elements.CategorySingle;
import blog.synload.com.elements.Index;

import com.synload.eventsystem.events.RequestEvent;
import com.synload.framework.modules.annotations.Event;
import com.synload.framework.modules.annotations.Event.Type;

public class WShandler {
	@Event(name="getIndex",description="Index of the blog",trigger={"get","index"},type=Type.WEBSOCKET)
	public void getIndex(RequestEvent event){
		event.getSession().send(new Index(event.getSession(),event.getRequest().getTemplateCache()));
	}
	@Event(name="getCategories",description="Get category listings",trigger={"get","categories"},type=Type.WEBSOCKET)
	public void getCategories(RequestEvent event){
		event.getSession().send(
			new Categories(
				event.getSession(),
				event.getRequest().getTemplateCache(),
				event.getRequest().getData().get("partial")
			)
		);
	}
	@Event(name="getCategory",description="Get single category entries",trigger={"get","category"},type=Type.WEBSOCKET)
	public void getCategory(RequestEvent event){
		event.getSession().send(
			new CategorySingle(
				event.getSession(),
				event.getRequest().getTemplateCache(),
				event.getRequest().getData().get("categoryId")
			)
		);
	}
}
