package blog.synload.com.sql;

import java.sql.ResultSet;

import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.LongBlobColumn;
import com.synload.framework.modules.annotations.sql.StringColumn;
import com.synload.framework.sql.Model;

@SQLTable(description = "Categories for the blog entries", name = "Category", version = 0.1)
public class Category extends Model {
	public Category(ResultSet rs) {
		super(rs);
	}
	public Category(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10,AutoIncrement=true,Key=true)
	public long id;
	
	@HasMany(of=Entry.class,key="id")
	@LongBlobColumn()
	public String entries;
	
	@StringColumn(length = 40)
	public String title;
	
	@StringColumn(length = 120)
	public String description;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEntries() {
		return entries;
	}
	public void setEntries(String entries) {
		this.entries = entries;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
