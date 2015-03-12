package blog.synload.com.sql;

import java.sql.ResultSet;

import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.HasOne;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.sql.BigIntegerColumn;
import com.synload.framework.modules.annotations.sql.LongBlobColumn;
import com.synload.framework.sql.Model;
import com.synload.framework.users.User;

@SQLTable(description = "Replies to the entries", name = "Replies", version = 0.1)
public class Reply extends Model {
	public Reply(ResultSet rs) {
		super(rs);
	}
	public Reply(Object... data){
		super(data);
	}
	
	@BigIntegerColumn(length = 10,AutoIncrement=true,Key=true)
	public long id;
	
	@LongBlobColumn()
	public String comment;
	
	@HasMany(of=Entry.class,key="id")
	@LongBlobColumn()
	public String entries;
	
	@HasOne(of=User.class,key="id")
	@BigIntegerColumn(length=20)
	public long user;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEntries() {
		return entries;
	}
	public void setEntries(String entries) {
		this.entries = entries;
	}
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	
	
}