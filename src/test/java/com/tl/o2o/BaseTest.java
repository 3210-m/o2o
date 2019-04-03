package com.tl.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Scanner;

/**
 * 测试基类，配置spring和Junit整合，Junit启动时加载springIOC容器
 *
 * @author tangli
 * @create 2018-11-03 下午3:58
 **/

//告诉junit，spring的配置文件在哪
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		int j = sc.nextInt();
		if((i+1)%j==0){
			System.out.println("sssssssssss");
		}else{
			System.out.println("xxxxxxxxxxxx");
		}
	}
}
