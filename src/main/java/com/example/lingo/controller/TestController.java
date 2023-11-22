package com.example.lingo.controller;

import com.example.lingo.common.Result;
import com.example.lingo.dto.LoginInfoDto;
import com.example.lingo.dto.MessageDto;
import com.example.lingo.model.TMessage;
import com.example.lingo.model.TUser;
import com.example.lingo.model.TWord;
import com.example.lingo.service.MemoryService;
import com.example.lingo.service.MessageService;
import com.example.lingo.service.UserService;
import com.example.lingo.service.WordService;
import com.example.lingo.util.BcryptUtil;
import com.example.lingo.util.JsonExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.lingo.util.JwtUtil.getUserId;

@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private WordService wordService;
    @Autowired
    private MemoryService  memoryService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/user/register")
    public Result<TUser> register(@RequestBody TUser user) {
        return userService.register(user);
    }

    @PostMapping("/user/login")
    public Result login(@RequestBody LoginInfoDto loginInfoDto) {
        // 从数据库中查找用户的信息，信息正确生成token
        return userService.login(loginInfoDto);
    }
    @GetMapping("/user/getUserData")
    public Result getUserData(HttpServletRequest request) {
        //从头信息中查找token
        String token = request.getHeader("Authorization");
        return userService.getUserData(Long.valueOf(getUserId(token)));
    }
    @GetMapping("wordApply/{tableId}/{wordId}")
    public Result wordApply(@PathVariable("tableId") Long tableId, @PathVariable("wordId") Long wordId) {
        return wordService.getTenWords(tableId,wordId);
    }
    @GetMapping("wordFinish/{tableId}/{wordId}")
    public Result wordFinish(@PathVariable("tableId") Long tableId, @PathVariable("wordId") Long wordId,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return memoryService.insertMemory(Long.valueOf(getUserId(token)),tableId,wordId);
    }
    @PostMapping("/message")
    public Result message(@RequestBody MessageDto message,HttpServletRequest request) {
//        System.out.println(message);
        String token = request.getHeader("Authorization");
        String userId = getUserId(token);
        return messageService.deal(message,userId);
    }
    @GetMapping("/test/insertWord")
    public Result insertWord() {
        //读取json文件
        //插入数据库
        File jsonFile = new File("src/main/resources/en_packed4.json");  // 替换为实际的 JSON 文件路径

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            System.out.println(rootNode.size());
            System.out.println(rootNode.get(0));
            List<String> namelist = new ArrayList<>();
            for(int i = 1 ; i <= rootNode.size() ; i ++){
                TWord tWord = new TWord();
                tWord.setWord(rootNode.get(i-1).get("word").asText());
                tWord.setTableId(1L);
                tWord.setDefinition(rootNode.get(i-1).get("translations").asText());
                tWord.setPhrase(rootNode.get(i-1).get("prompt2").asText());
//                System.out.println(tWord.getPhrase());
                tWord.setPhraseExplain(rootNode.get(i-1).get("phrase_explain").asText());
                String imagename = rootNode.get(i-1).get("word").asText();
                //根据imagename作为前缀匹配/static/jp_images/下的图片文件名，并且输出
                File[] files = new File("src/main/resources/static/en_images_1/").listFiles();
                int flag =0;
                for (File file : files) {
                    if (file.isFile() && file.getName().startsWith(imagename)) {
                        flag =1;
//                        System.out.println(file.getName());
                        String fullname = file.getName();
//                        System.out.println(fullname);
                        tWord.setPhraseImage(fullname);
                        break;
                    }
                }
                wordService.insertWord(tWord);
            }
//            System.out.println(namelist.size());
            // 在这里可以根据需要处理提取到的 JSON 数据
            // 例如，获取特定字段的值：
//            String value = rootNode.get("field").asText();
//            System.out.println(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success("insertWord");
    }
    @GetMapping("/test")
    public Result test() {
        return Result.success("test");
    }
}
