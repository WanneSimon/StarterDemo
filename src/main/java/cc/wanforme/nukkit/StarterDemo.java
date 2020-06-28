package cc.wanforme.nukkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterDemo extends NukkitApplicationLauncher{
	private static final Logger log = LoggerFactory.getLogger(StarterDemo.class);
	
	public static void main(String[] args) {
		System.out.println("starting !");
//		SpringApplication.run(StarterDemo.class, args);
		launchNukkit(StarterDemo.class, args);
		log.info("\n<<<<<<<<<<<<<<<<\tserver started\t>>>>>>>>>>>>>>>");	
	}
	
	
}
