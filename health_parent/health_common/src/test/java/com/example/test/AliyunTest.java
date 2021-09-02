package com.example.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.Test;

import java.io.File;

public class AliyunTest {

//     @Test
//     public void test01() {
//         // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//         String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//         // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//         String accessKeyId = "LTAI5tRh3KWbYH1AwDn5s4aP";
//         String accessKeySecret = "MGFvu4yFVdq8znbKFWpJ7nCISIcjfh";
//
//         // 创建OSSClient实例。
//         OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//     // 创建PutObjectRequest对象。
//     // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
//     // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//     PutObjectRequest putObjectRequest =
//         new PutObjectRequest(
//             "health-jack", "test/003.png", new File("C:\\Users\\Jacka\\Videos\\003.png"));
//
//         // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//         // ObjectMetadata metadata = new ObjectMetadata();
//         // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//         // metadata.setObjectAcl(CannedAccessControlList.Private);
//         // putObjectRequest.setMetadata(metadata);
//
//         // 上传文件。
//         ossClient.putObject(putObjectRequest);
//
//         // 关闭OSSClient。
//         ossClient.shutdown();
//     }
//
//     @Test
//     public void test2() {
//         // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//         String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//         // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//         String accessKeyId = "LTAI5tRh3KWbYH1AwDn5s4aP";
//         String accessKeySecret = "MGFvu4yFVdq8znbKFWpJ7nCISIcjfh";
//
// // 填写Bucket名称。
//         String bucketName = "health-jack";
// // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
//         String objectName = "test/003.png";
//
// // 创建OSSClient实例。
//         OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
// // 删除文件或目录。如果要删除目录，目录必须为空。
//         ossClient.deleteObject(bucketName, objectName);
//
// // 关闭OSSClient。
//         ossClient.shutdown();
//
//     }
}
