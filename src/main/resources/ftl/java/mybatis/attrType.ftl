package ${packageName!"com.wind.entity.base"};

/**
 * 条件类型
 * @author wind
 *
 */
public enum AttrType {
	
	IN("in"),
	
	BETWEEN("between"),
	
	SINGLE("single"),
	
	NO("no"),
	
	;
	
	private String name;
	
	private AttrType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
