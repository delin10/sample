package nil.ed.springboot_servlet_sample.servlet;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import nil.ed.springboot_servlet_sample.processor.ByteBufferInputStream;
import nil.ed.springboot_servlet_sample.processor.FormDataProcessor;
import nil.ed.springboot_servlet_sample.processor.IMultiPartResolver;
import nil.ed.springboot_servlet_sample.processor.MultiPartResolverImpl;
import nil.ed.springboot_servlet_sample.processor.fileitem.AbstractMemoryFileItem;
import nil.ed.springboot_servlet_sample.processor.fileitem.ByteBufferOutputStream;
import nil.ed.springboot_servlet_sample.processor.fileitem.DirectMemoryFileItemFactory;
import nil.ed.springboot_servlet_sample.processor.fileitem.HeapMemoryFileItemFactory;
import nil.ed.springboot_servlet_sample.util.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * @author lidelin
 * @date 2019/07/24 14:13
 */
@WebServlet("/test")
/**
 * 必须加这个注解，否则无法解析
 */
//@MultipartConfig
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            System.out.println(headerName + ":" + req.getHeader(headerName));
        }
        System.out.println("xwl的查询参数：" + req.getParameter("xwl"));
        System.out.println("param map:");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.ISO_8859_1));
//        String line = null;
//        while ((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
        testMemoryFileResolver(req);
        req.getParameterMap().entrySet().forEach(stringEntry -> {
            System.out.print(stringEntry.getKey() + ":");
            String values = Arrays.stream(stringEntry.getValue()).collect(Collectors.joining(","));
            System.out.println(values);
        });
        System.out.println("parts size:" + req.getParts().size());
//        req.getParts().stream().forEach(part -> {
//            System.out.println(part.getContentType());
//            System.out.println(part.getName());
//            System.out.println(part.getSize());
//            System.out.println(part.getSubmittedFileName());
//        });
        resp.getWriter().println("ok");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Get");
    }

    private void testMemoryFileResolver(HttpServletRequest request) {
        FileItemFactory factory = new DirectMemoryFileItemFactory();
        FileItemFactory heapMemoryFileItemFactory = new HeapMemoryFileItemFactory();
        IMultiPartResolver resolver = new MultiPartResolverImpl(factory);
        resolver.resolve(request);
        resolver.getFileParts().entrySet().stream().forEach(System.out::println);
        resolver.getStringParts().entrySet().stream().forEach(System.out::println);
        final String base_path = "D:/temp";
        resolver.getItems().stream().map(item -> (AbstractMemoryFileItem)item).filter(item -> !item.isFormField()).forEach(file -> {
            System.out.println(file);
            try(OutputStream outputStream = Files.newOutputStream(Paths.get(base_path,file.getName()+Instant.now().getNano()), StandardOpenOption.CREATE)){
                outputStream.write(file.get());
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        });
    }


    private String publish(HttpServletRequest request) {
        String root = "D:/";
        FileUtil.clearDir(root);
        FormDataProcessor processor = new FormDataProcessor("D:/temp");
        processor.process(request);
        //processor.process2(request);
        System.out.println(processor.getFormTextFields(StandardCharsets.UTF_8.name()));
        StringBuilder imgurl = new StringBuilder();
        processor.handleFileFields((item, i) -> {
            String fileName = item.getName();
            if (!fileName.trim().isEmpty()) {
                String ext = fileName.substring(fileName.lastIndexOf("."));
                String name = FileUtil.generateFile(root, () -> {
                    return String.valueOf(Instant.now().getEpochSecond());
                });
                String relUrl = name + ext;
//                imgurl.addLast(relUrl + ";");
//                File file = FileUtil.newFile(root+ "/" + relUrl);
//                try {
//                    item.write(file);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }
        });
        System.out.println(imgurl.toString());
        return imgurl.toString();
    }
}
