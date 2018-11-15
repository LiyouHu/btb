import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 测试流程引擎
 * @ClassName: TestProcessEngine 
 * @Description: TODO
 * @author: huliyou
 * @date: 2018年11月1日 下午7:13:25
 */

public class TestProcessEngine {

	@Test
	public void testCreateTable() {
		String resource = "activiti-context.xml";
		String beanName = "processEngineConfiguration";
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(resource, beanName);
		ProcessEngine processEngine = configuration.buildProcessEngine();
	}

}
