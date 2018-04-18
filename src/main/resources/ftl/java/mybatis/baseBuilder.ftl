package ${packageName!"com.wind.entity.base"};

import java.util.ArrayList;
import java.util.List;

/**
 * 顶层条件构建器
 * @author wind
 *
 */
public abstract class BaseBuilder {
	
	private List<Attribute> attrs;

	public BaseBuilder() {
		this.attrs = new ArrayList<Attribute>();
	}
	
	/**
	 * 清空sql条件
	 */
	public void clear(){
		attrs.clear();
	}
	
	/**
	 * 生成sql条件
	 * @return
	 */
	public Condition build(){
		Condition condition = new Condition();
		condition.setAttrs(attrs);
		return condition;
	}
	
	public void addAttr(Attribute attr){
		attrs.add(attr);
	}
	
}
