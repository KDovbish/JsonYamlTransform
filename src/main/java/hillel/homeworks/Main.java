package hillel.homeworks;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        //  Определям папку из которой будут браться файлы для конвертации
        String workFolder;
        if (args.length == 0) {
            workFolder = FilesService.getCurrentFolder();
        } else {
            workFolder = args[0];
            if (!workFolder.endsWith(File.separator)) workFolder = workFolder + File.separator;
            if (!(new File(workFolder).isDirectory())) {
                System.out.println(workFolder + " : Folder is not found!");
                return;
            }
        }

        //  Определяем папку, куда будут складываться сконвертированные файлы
        String convertedFolder = FilesService.getCurrentFolder() + "converted" + File.separator;
        FilesService.mkdir(convertedFolder);

        //  Объект для трансформации json <-> yaml
        TransformService transformService = new TransformService();

        //  Объект для логирования
        Logging logging = new Logging(FilesService.getCurrentFolder() + "result.log");

        File[] files;
        //  Конвертация всех файлов json
        files = FilesService.getFiles(workFolder, "json");
        for (File inputFile: files) {
            transformService.JsonToYaml(inputFile, new File(convertedFolder + FilesService.setFileExt(inputFile.getName(), "yaml")));
            logging.println(transformService.getLastTransformStateAsText());
        }
        //  Конвертация всех файлов yaml
        files = FilesService.getFiles(workFolder, "yaml");
        for (File inputFile: files) {
            transformService.YamlToJson(inputFile, new File(convertedFolder + FilesService.setFileExt(inputFile.getName(), "json")));
            logging.println(transformService.getLastTransformStateAsText());
        }

        //  Закрываем файл лога
        logging.close();
    }
}
