package com.example.lingo.util;

import com.example.lingo.mapper.TWordMapper;
import com.example.lingo.model.TWord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonExtractor {
    @Autowired
    private TWordMapper tWordMapper;
    public void insert() {
        File jsonFile = new File("src/main/resources/jp_packed2.json");  // 替换为实际的 JSON 文件路径

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            System.out.println(rootNode.size());
            System.out.println(rootNode.get(0));
            List<String> namelist = new ArrayList<>();
            for(int i = 1 ; i <= rootNode.size() ; i ++){
                TWord tWord = new TWord();
                tWord.setWord(rootNode.get(i-1).get("word").asText());
                tWord.setTableId(2L);
                tWord.setDefinition(rootNode.get(i-1).get("definition").asText());
                tWord.setPhonetic(rootNode.get(i-1).get("pron").asText());
                tWord.setPhrase(rootNode.get(i-1).get("prompt2").asText());
                tWord.setPhraseExplain(rootNode.get(i-1).get("phrase_explain").asText());
                String imagename = rootNode.get(i-1).get("word").asText();
                //根据imagename作为前缀匹配/static/jp_images/下的图片文件名，并且输出
                File[] files = new File("src/main/resources/static/jp_images/").listFiles();
                int flag =0;
                for (File file : files) {
                    if (file.isFile() && file.getName().startsWith(imagename)) {
                        flag =1;
//                        System.out.println(file.getName());
                        String fullname = file.getName();
                        namelist.add(fullname);
                        System.out.println(fullname);
                        //根据fullname获取最后一个_后面的文件名
                        int pos = fullname.lastIndexOf("_");
                        String filename = fullname.substring(pos+1);
                        //将filename设置为这个这个文件的新文件名
                        file.renameTo(new File("src/main/resources/static/jp_images/" + filename));
//                        tWord.setPhraseImage(filename);
                        break;
                    }
                }


//                System.out.println(tWord);
                tWordMapper.insert(tWord);
//                tWord.setPhraseImage();
            }
            System.out.println(namelist.size());
            // 在这里可以根据需要处理提取到的 JSON 数据
            // 例如，获取特定字段的值：
//            String value = rootNode.get("field").asText();
//            System.out.println(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JsonExtractor jsonExtractor = new JsonExtractor();
        jsonExtractor.insert();
    }
}
