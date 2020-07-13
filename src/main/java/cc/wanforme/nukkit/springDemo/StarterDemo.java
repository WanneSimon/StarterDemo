package cc.wanforme.nukkit.springDemo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableSet;

import cc.wanforme.nukkit.spring.NukkitApplicationLauncher;
import cn.nukkit.Nukkit;


@SpringBootApplication
public class StarterDemo extends NukkitApplicationLauncher{
	private static final Logger log = LoggerFactory.getLogger(StarterDemo.class);
	
	public static void main(String[] args) {
		System.out.println("starting !");
//		SpringApplication.run(StarterDemo.class, args);
		launchNukkit(StarterDemo.class, args);
		log.info("\n<<<<<<<<<<<<<<<<\tserver started\t>>>>>>>>>>>>>>>");
		stt();
	}
	
	private static void stt() {
//		LocaleManager from = LocaleManager.from("locale/nukkit/languages.json",
//	            "locale/nukkit/texts", "locale/vanilla");
		String languagesPath = "locale/nukkit/languages.json";
		String[] textPaths = {"locale/nukkit/texts", "locale/vanilla"};
		// BOOT-INF/lib/nukkit-2.0.0-SNAPSHOT.jar!/locale/nukkit/languages.json
		// jar:file:/C:/Users/hasee/.m2/repository/cn/nukkit/nukkit/2.0.0-SNAPSHOT/nukkit-2.0.0-SNAPSHOT.jar!/locale/nukkit/languages.json
		// jar:file:/C:/**/nukkit-2.0.0-SNAPSHOT.jar!/locale/nukkit/languages.json
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        try {
            URI uri = classLoader.getResource(languagesPath).toURI();
            Path langsPath;
            try {
                langsPath = Paths.get(uri);
            } catch (FileSystemNotFoundException e) {
                FileSystems.newFileSystem(uri, new HashMap<>());
                langsPath = Paths.get(uri);
            }

            Path[] paths = new Path[textPaths.length];
            for (int i = 0; i < textPaths.length; i++) {
                paths[i] = Paths.get(classLoader.getResource(textPaths[i]).toURI());
            }
//            return from(langsPath, paths);
            
            ImmutableSet.Builder<Locale> builder = ImmutableSet.builder();
//            try (InputStream stream = Files.newInputStream(path)) {
           	try (InputStream stream = Files.newInputStream(langsPath)) {
                JsonNode array = Nukkit.JSON_MAPPER.readTree(stream);
                for (JsonNode element : array) {
                    builder.add(getLocaleFromString(element.textValue()));
                }
            } catch (IOException e) {
            	e.printStackTrace();
                throw new IllegalArgumentException("Unable to load language list", e);
            }
//            return builder.build();
            
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println("success！- 1");
        
	}
	
    public static Locale getLocaleFromString(String localeString) {
        checkNotNull(localeString, "localeString");
        String[] codes = localeString.split("_");
        checkArgument(codes.length == 2, "Invalid language country code");
        return new Locale(codes[0], codes[1]);
    }
}
