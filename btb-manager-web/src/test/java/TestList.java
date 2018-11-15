import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.btb.pojo.Emp;

public class TestList {

	public TestList() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void test() {
		List<Emp> list = new ArrayList<Emp>();
		Emp emp = new Emp();
		emp.setEname("zhangsan");
		list.add(emp);
		System.out.println(list);
	}
	
}
