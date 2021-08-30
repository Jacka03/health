package com.example.test;

import org.junit.Test;

public class ALiYunTest {

    // @Test
    // public void test01() {
    //     // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    //     String endpoint = "yourEndpoint";
    //     // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    //     String accessKeyId = "yourAccessKeyId";
    //     String accessKeySecret = "yourAccessKeySecret";
    //
    //     // 创建OSSClient实例。
    //     OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    //
    //     // 创建PutObjectRequest对象。
    //     // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
    //     // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
    //     PutObjectRequest putObjectRequest = new PutObjectRequest("examplebucket", "exampledir/exampleobject.txt", new File("D:\\localpath\\examplefile.txt"));
    //
    //     // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
    //     // ObjectMetadata metadata = new ObjectMetadata();
    //     // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
    //     // metadata.setObjectAcl(CannedAccessControlList.Private);
    //     // putObjectRequest.setMetadata(metadata);
    //
    //     // 上传文件。
    //     ossClient.putObject(putObjectRequest);
    //
    //     // 关闭OSSClient。
    //     ossClient.shutdown();
    // }
}
