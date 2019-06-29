package nil.ed.file;

import java.io.File;

public class FileUtils {
    public static String getProjectAbsolutePath(String fileRelativePath) {
        return new File(fileRelativePath).getAbsolutePath();
    }
}
