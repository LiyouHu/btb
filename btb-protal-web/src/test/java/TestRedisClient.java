import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.btb.jedis.JedisClient;
import com.btb.jedis.JedisClientPool;

public class TestRedisClient {
	
	@Test
	public void testRedisClient() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-redis.xml");
		JedisClient jedisClient = (JedisClient)context.getBean(JedisClientPool.class);
		jedisClient.hset("inventory:3", "123213", "iteminfo3");
	}
	
	@Test
	public void testExists() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-redis.xml");
		JedisClient jedisClient = (JedisClient)context.getBean(JedisClientPool.class);
		System.out.println(jedisClient.exists("INVENTORY:2:46546464654"));
	}
	
	@Test
	public void testString() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-redis.xml");
		JedisClient jedisClient = (JedisClient)context.getBean(JedisClientPool.class);
		jedisClient.set("INVENTORY:2:55325255", "iteminfo2");
	}
	
	@Test
	public void testKeys() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-redis.xml");
		JedisClient jedisClient = (JedisClient)context.getBean(JedisClientPool.class);
		Set<String> keys = jedisClient.keys("INVENTORY:2:*");
		for (String string : keys) {
			System.out.println(string);
		}
	}
}
