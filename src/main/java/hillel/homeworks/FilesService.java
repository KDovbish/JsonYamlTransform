package hillel.homeworks;

import java.io.File;

public class FilesService {


    static File[] getFiles(String folderName, String ext) {
        File folder = new File(folderName);
        return folder.listFiles((d, n) -> n.endsWith("." + ext)?true:false);
    }

    /**
     * Получить текущий каталог
     * @return Текущий каталог
     */
    static String getCurrentFolder() {
        String folder = System.getProperty("user.dir");
        if (folder.endsWith(File.separator)) return folder ;
        else return folder + File.separator;
    }

    static String setFileExt(String fileName, String ext) {
        return fileName.substring(0, fileName.lastIndexOf(".")) + "." + ext;
    }

    static void mkdir(String folder) {
        new File(folder).mkdir();
    }

}
