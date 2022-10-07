package com.example.cdn1.controller;

import com.example.cdn1.App;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.CreateFolderRequest;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.FileUpdateRequest;
import io.imagekit.sdk.models.GetFileListRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.models.results.ResultEmptyBlock;
import io.imagekit.sdk.models.results.ResultList;
import io.imagekit.sdk.utils.Utils;
import net.iharder.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/upload")
public class BaseController {

    private static final Path CURRENT_POLDER = Paths.get(System.getProperty("user.dir"));

    @GetMapping
    public String hello(){
        return "Hello";
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam MultipartFile image) throws Exception{
//        Path staticPath = Paths.get("static");
//        Path imagePath = Paths.get("images");
//        System.out.println(CURRENT_POLDER.toString());
//        if(!Files.exists(CURRENT_POLDER.resolve(staticPath).resolve(imagePath))){
//            try {
//                Files.createDirectories(CURRENT_POLDER.resolve(staticPath).resolve(imagePath));
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//        Path file = CURRENT_POLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());

//        try {
//            OutputStream os = Files.newOutputStream(file);
//            os.write(image.getBytes());
//        } catch (IOException e) {
//            System.out.println(e);
//        }
        ImageKit imageKit = ImageKit.getInstance();
        Configuration config= Utils.getSystemConfig(App.class);
        imageKit.setConfig(config);
//        byte [] bytes = Files.readAllBytes(Paths.get("static/images/" + image.getOriginalFilename()));
        FileCreateRequest fileCreateRequest = new FileCreateRequest(image.getBytes(), image.getOriginalFilename().toString());
        fileCreateRequest.setUseUniqueFileName(false);
        fileCreateRequest.setFolder("user/user1");
        Result result = ImageKit.getInstance().upload(fileCreateRequest);
//        System.out.println(result.toString());
        return result.getFileId();
    }

    @GetMapping("/getImage")
    public String getList() throws Exception{
        ImageKit imageKit = ImageKit.getInstance();
        Configuration configuration = Utils.getSystemConfig(App.class);
        System.out.println(Base64.encodeBytes(configuration.getPrivateKey().getBytes()));
        imageKit.setConfig(configuration);
        System.out.println(imageKit.getAuthenticationParameters());
        System.out.println(imageKit.getAuthenticationParameters("07cd5c05-6dba-4c92-b9f0-e88c75439e3c",134114));
        String[] tags = new String[3];
        tags[0] = "tag-1";
        tags[1] = "tag-2";
        tags[2] = "tag-3";
        GetFileListRequest getFileListRequest = new GetFileListRequest();
        getFileListRequest.setType("file");
        getFileListRequest.setSort("ASC_CREATED");
        getFileListRequest.setPath("/user/");
//        getFileListRequest.setSearchQuery("createdAt >= '2d' OR size < '2mb' OR format='png'");
        getFileListRequest.setFileType("all");
//        getFileListRequest.setLimit("4");
//        getFileListRequest.setSkip("1");
//        getFileListRequest.setTags(tags);
        ResultList resultList = ImageKit.getInstance().getFileList(getFileListRequest);
        System.out.println(resultList.getResults());
//        Result result = ImageKit.getInstance().getFileDetail("633d046abf51c1dc80f02808");
//        System.out.println(result.getName());
        return "Hello";
    }

    @PostMapping("/update")
    public String updateImage()throws Exception{
        String fileId = "633e571cbf51c1dc805b6900";
        List<String> tags = new ArrayList<>();
        tags.add("tag-1");
        tags.add("tag-23");
        List<String> aiTags = new ArrayList<>();
        aiTags.add("ai-tag-1");
        aiTags.add("ai-tag-2");
        FileUpdateRequest fileUpdateRequest = new FileUpdateRequest(fileId);
        fileUpdateRequest.setTags(tags);
        fileUpdateRequest.setRemoveAITags(aiTags);
        fileUpdateRequest.setWebhookUrl("url");
        Result result = ImageKit.getInstance().updateFileDetail(fileUpdateRequest);
        ImageKit.getInstance().getAuthenticationParameters("abcded",30000);
        return "Hello";
    }

    @PostMapping("/create")
    public String create() throws Exception{
        CreateFolderRequest createFolderRequest = new CreateFolderRequest();
        createFolderRequest.setFolderName("image");
        createFolderRequest.setParentFolderPath("source/folder/path");
        ImageKit.getInstance().createFolder(createFolderRequest);
//        ResultEmptyBlock resultEmptyBlock = ImageKit.getInstance().createFolder(createFolderRequest);
        return "success";
    }

    @DeleteMapping("/delete")
    public String delete() throws Exception{
        ImageKit imageKit = ImageKit.getInstance();
        Configuration configuration = new Configuration();
//        configuration.setPublicKey("public_0yyDvAnbTDG1gu9qfSPEWlbwdHo=");
        configuration.setPrivateKey("private_X0l7aZRxUoFD0B7OsYN1LxIUt/A=");
        configuration.setUrlEndpoint("https://ik.imagekit.io/hzjjknhd0");
        imageKit.setConfig(configuration);
        Result result = ImageKit.getInstance().deleteFile("633e571cbf51c1dc805b6900");
        return result.getUrl();
    }

}
