package ${packageName!"com.wind.entity.base"};

import java.util.List;

/**
 * sql where条件从句
 * @author wind
 *
 */
public class Condition {
	
	private List<Attribute> attrs;

	public List<Attribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attribute> attrs) {
		this.attrs = attrs;
	}
	
	public boolean isValid() {
        return attrs != null && attrs.size() > 0;
    }
}
