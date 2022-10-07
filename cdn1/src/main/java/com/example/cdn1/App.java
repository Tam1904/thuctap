package com.example.cdn1;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.*;
import io.imagekit.sdk.models.results.*;
import io.imagekit.sdk.utils.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static String local = "src/main/resources";
    private static final Path CURRENT_POLDER = Paths.get(System.getProperty("user.dir"));
    public static void main(String[] args) {

        try {
            ImageKit imageKit = ImageKit.getInstance();
            Configuration config=Utils.getSystemConfig(App.class);
            imageKit.setConfig(config);
            FileCreateRequest fileCreateRequest =new FileCreateRequest("https://ik.imagekit.io/ikmedia/red_dress_woman.jpeg",  "women_in_red.jpg");
            List<String> responseFields=new ArrayList<>();
            responseFields.add("thumbnail");
            responseFields.add("tags");
            responseFields.add("customCoordinates");
            fileCreateRequest.setResponseFields(responseFields);
            List<String> tags=new ArrayList<>();
            tags.add("Software");
            tags.add("Developer");
            tags.add("Engineer");
            fileCreateRequest.setTags(tags);
//            Result result=ImageKit.getInstance().upload(fileCreateRequest);
            System.out.println("Thành công");
//            Map<String,Object> options = new HashMap<>();
//            options.put("path",base)
//            String image_url = ImageKit.getInstance().getUrl(options);

            /*
            tạo ảnh có đường dẫn và kích thước mình tự đặt
            */

            List<Map<String,String>> transformation = new ArrayList<>();
            Map<String,String> scale = new HashMap<>();
            scale.put("height","200");
            scale.put("width","200");
            scale.put("raw", "ar-4-3,q-40");
            transformation.add(scale);
//            Map<String,String> rotate = new HashMap<>();
//            rotate.put("rt","90");
//            transformation.add(rotate);
            Map<String,Object> options = new HashMap<>();
            options.put("path", "/default-image.jpg");
            options.put("transformation", transformation);
            options.put("transformationPosition","query");
            Map<String,String> format = new HashMap<>();
            format.put("format","jsp");
            format.put("progressive","true");
            format.put("effectSharpen","-");
            format.put("effectContrast","1");
            transformation.add(format);

            /*
            tạo URL đã ký của ảnh đó sẽ hết hạn sau 1 số giây nhất định
             */
            options.put("signed",true);
            options.put("expireSeconds",10);
            String image_url=ImageKit.getInstance().getUrl(options);
            System.out.println(image_url); // trả về ảnh có kích thước


            /*
            Add Tags
             */

            List<String> fileIds = new ArrayList<>();
            fileIds.add("file_id_1");
            fileIds.add("file_id_2");
            List<String> tag2 = new ArrayList<>();
            tag2.add("tag-to-add-1");
            tag2.add("tag-to-add-2");
            TagsRequest tagsRequest = new TagsRequest(fileIds, tag2);
            tagsRequest.setFileIds(fileIds);
            tagsRequest.setTags(tag2);
            ResultTags resultTags = ImageKit.getInstance().addTags(tagsRequest);

            /*
            Remove Tags
             */

            List<String> fileIds2 = new ArrayList<>();
            fileIds.add("file_id_1");
            fileIds.add("file_id_2");
            List<String> tags3 = new ArrayList<>();
            tags3.add("tag-to-remove-1");
            tags3.add("tag-to-remove-2");
            TagsRequest tagsRequest2 = new TagsRequest(fileIds2, tags3);
            ResultTags resultTags2 = ImageKit.getInstance().removeTags(tagsRequest2);
            /*

            Remove AI Tags
             */
            List<String> fileIds3 = new ArrayList<>();
            fileIds3.add("file_id_1");
            fileIds3.add("file_id_2");
            List<String> aiTags = new ArrayList<>();
            aiTags.add("ai-tag-to-remove-1");
            aiTags.add("ai-tag-to-remove-2");
            AITagsRequest aiTagsRequest = new AITagsRequest();
            aiTagsRequest.setFileIds(fileIds);
            aiTagsRequest.setAITags(aiTags);
            ResultTags resultTags3 = ImageKit.getInstance().removeAITags(aiTagsRequest);

            /*
                Delete File
             */
            Result result=ImageKit.getInstance().deleteFile("file_id");


            /*
            Delete Files (Bulk) API
            */
            List<String> fileIds5 = new ArrayList<>();
            fileIds.add("file-id-1");
            fileIds.add("file-id-2");
            ResultFileDelete resultss = ImageKit.getInstance().bulkDeleteFiles(fileIds5);


            /*
            Move File API
            */

            MoveFileRequest moveFileRequest = new MoveFileRequest();
            moveFileRequest.setSourceFilePath("/your-file-name.jpg");
            moveFileRequest.setDestinationPath("/sample-folder/");
            ResultNoContent resultNoContent = ImageKit.getInstance().moveFile(moveFileRequest);

            /*
                Create Folder API
             */
            CreateFolderRequest createFolderRequest = new CreateFolderRequest();
            createFolderRequest.setFolderName("new-folder");
            createFolderRequest.setParentFolderPath("/");
            ResultEmptyBlock resultEmptyBlock = ImageKit.getInstance().createFolder(createFolderRequest);
        }
        catch (Exception e) {
            System.out.println("Lỗi");
        }
    }
}