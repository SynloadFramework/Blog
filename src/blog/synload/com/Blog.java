package blog.synload.com;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

import blog.synload.com.sql.Category;
import blog.synload.com.sql.Entry;

import com.synload.framework.Log;
import com.synload.framework.modules.ModuleClass;
import com.synload.framework.modules.annotations.Module;
import com.synload.framework.users.User;
import com.synload.framework.ws.WSHandler;

@Module(author="Nathaniel Davidson", name="SyBlog", version="0.1a")
public class Blog extends ModuleClass{
	public static TextProcessor bbcode = BBProcessorFactory.getInstance().create(new File("bbcodes.xml"));
	@Override
	public void initialize() {
		try {
			if(Category._find(Category.class, "").exec(Category.class).size()==0){
				Log.info("Created default category!", this.getClass());
				Category c = new Category("title","Welcome","description","welcome message");
				c._insert();
				Category c2 = new Category("title","Updates","description","updates to sites");
				c2._insert();
				Category c3 = new Category("title","Blank Category","description","nothing to see here!");
				c3._insert();
				Entry e = new Entry(
					"title", "Hello World!",
					"body", "Welcome to my new blog. I am still setting everything up, please be patient!",
					"publicPost", 1,
					"date", ( System.currentTimeMillis() / 1000 ) + 1
				);
				e._insert();
				c._set(e);
				Entry es = new Entry(
					"title", "Site Change!",
					"body", "Weeeow!",
					"publicPost", 1,
					"date", ( System.currentTimeMillis() / 1000 ) + 1
				);
				es._insert();
				c._set(es);
				c2._set(es);
				Log.debug("Created default entry and set category to category!", this.getClass());
				String pass = WSHandler.getRandomHexString(10);
				//Authentication.create(, , , new ArrayList<String>(), 1);
				User u;
				try {
					long timestamp = System.currentTimeMillis() / 1000;
					u = new User(
						"username", "admin", 
						"password", User.hashGenerator(pass), 
						"email", "default@site.com", 
						"flags", "", 
						"admin", 1, 
						"created_date", timestamp
					);
					u._insert();
					e._set(u);
					Log.info("Created Default User admin["+pass+"]", this.getClass());
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void crossTalk(Object... obj) {
		
	}

}
