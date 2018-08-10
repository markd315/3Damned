package io.swagger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.BlacklistApiController;
import io.swagger.api.UserApi;
import io.swagger.api.UserApiController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.swagger.api.BlacklistApiController.hashPath;
import static io.swagger.api.BlacklistApiController.hashProdPath;
import static io.swagger.api.BlacklistApiController.hashTestPath;
import static io.swagger.api.UserApiController.userPath;
import static io.swagger.api.UserApiController.userProdPath;
import static io.swagger.api.UserApiController.userTestPath;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" })
public class Boot implements CommandLineRunner {

    public static final boolean testMode = true;

    public BlacklistApiController hashController = new BlacklistApiController();
    @Override
    public void run(String... arg0) {
        this.loadLists();

        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public void loadLists(){
        if(testMode){
            hashPath = hashTestPath;
            userPath = userTestPath;
        }else{
            hashPath = hashProdPath;
            userPath = userProdPath;
        }

        //Loading blacklists into memory
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = readFile(hashPath, Charset.defaultCharset());
            hashController.setHashBlacklist((Set<String>) mapper.readValue(json, new TypeReference<Set<String>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            json = readFile(userPath, Charset.defaultCharset());
            hashController.getUserController().setUserBlacklist((Set<String>) mapper.readValue(json, new TypeReference<Set<String>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Boot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
