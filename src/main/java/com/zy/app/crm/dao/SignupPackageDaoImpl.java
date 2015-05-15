package com.zy.app.crm.dao;

import com.zy.app.crm.model.SignupPackage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SignupPackageDaoImpl implements SignupPackageDao {

    @Value("${config.path}")
    private String configPath;

    @Override
    public SignupPackage findPackageByCode(String code) {

        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileInputStream(configPath+"/signup/packages/" +code+ ".yaml"), SignupPackage.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SignupPackage> getAllSignupPackages() {
        Yaml yaml = new Yaml();
        List<SignupPackage> result = new ArrayList<>();
        File dir = new File(configPath+"/signup/packages");
        File[] files = dir.listFiles((x, name) -> {
            return name.endsWith(".yaml");
        });

        try {
            for (File file : files) {
               SignupPackage signupPackage = yaml.loadAs(new FileInputStream(file), SignupPackage.class);
                result.add(signupPackage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
