package com.zy.app.fee.dao;

import com.zy.app.crm.model.SignupPackage;
import com.zy.app.fee.model.Fee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FeeDaoImpl implements FeeDao {

    @Value("${config.path}")
    private String configPath;


    @Override
    public Fee findFeeByCode(String code) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileInputStream(configPath+"/fees/"+code+ ".yaml"), Fee.class);
        } catch (Exception e) {
            throw new RuntimeException("unable to load fee", e);
        }
    }

    @Override
    public List<Fee> getAllFees() {
        Yaml yaml = new Yaml();
        List<Fee> result = new ArrayList<>();
        File dir = new File(configPath+"/fees");
        File[] files = dir.listFiles((x, name) -> {
            return name.endsWith(".yaml");
        });

        try {
            for (File file : files) {
                Fee fee = yaml.loadAs(new FileInputStream(file), Fee.class);
                result.add(fee);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
