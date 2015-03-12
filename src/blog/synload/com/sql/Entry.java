package blog.synload.com.sql;

import java.sql.ResultSet;

import blog.synload.com.Blog;

import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.HasOne;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.BooleanColumn;
import com.synload.framework.modules.annotations.sql.LongBlobColumn;
import com.synload.framework.modules.annotations.sql.StringColumn;
import com.synload.framework.sql.Model;
import com.synload.framework.users.User;

@SQLTable(description = "Entries into the blog", name = "Entry", version = 0.1)
public class Entry extends Model {
	public Entry(ResultSet rs) {
		super(rs);
		this.setHtml(Blog.bbcode.process(this.getBody()));
	}
	public Entry(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10,AutoIncrement=true,Key=true)
	public long id;
	
	@BooleanColumn()
	public boolean publicPost;
	
	@StringColumn(length = 70)
	public String title;
	
	@LongBlobColumn()
	public String body;
	
	@HasOne(of=User.class, key="id")
	@BigIntegerColumn(length=20)
	public long user;
	
	@HasMany(of=Category.class,key="id")
	@LongBlobColumn()
	public String categories;
	
	public String html;
	
	@BigIntegerColumn(length=11)
	public long date;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isPublicPost() {
		return publicPost;
	}
	public void setPublicPost(boolean publicPost) {
		this.publicPost = publicPost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	
}