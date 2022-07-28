package hillel.homeworks;

import java.io.File;

public class FilesService {

    /**
     * Получить массив дескрипторов файлов с заданным расширением в заданной папке
     * @param folderName Имя сканируемой папки
     * @param ext Искомое расширение без точки
     * @return массив объектов типа File
     */
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

    /**
     * Для заданного файла прописать заданное расширение
     * @param fileName Имя файла
     * @param ext Требуемое расширение без точки
     * @return Имя файла с новым расширением
     */
    static String setFileExt(String fileName, String ext) {
        return fileName.substring(0, fileName.lastIndexOf(".")) + "." + ext;
    }

    /**
     * Создать новую папку
     * @param folder Имя создаваемой папки
     */
    static void mkdir(String folder) {
        new File(folder).mkdir();
    }

}
