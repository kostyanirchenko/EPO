package lab1;

import model.FileWorker;

import java.io.FileNotFoundException;

/**
 * Created by Kostya Nirchenko.
 *
 * @since 13.03.2017
 */
public class Lab1 {

    public static long[] measures = new long[3];

    public static void measure(int i) {
        measures[i-1] = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        FileWorker.createFile("lab1.txt");
        measure(1);
        for (int i = 0; i < 1280; i++) {
            try {
                FileWorker.update(FileWorker.getFile(), i);
            } catch (FileNotFoundException e) {
                System.out.println("Такого файла не существует");
            }
        }
        measure(2);
        System.out.println(FileWorker.read(FileWorker.getFilename()));
        measure(3);
        showTimes();
    }

    public static void showTimes() {
        float writeTime = measures[1] - measures[0];
        float readTime = measures[2] - measures[1];
        System.out.println("Duration of write method: " + writeTime);
        System.out.println("Duration of read method: " + readTime);
        float writeTimePercent = (writeTime * 100)/ (writeTime + readTime);
        float readTimePercent = (readTime * 100) / (writeTime + readTime);
//        System.out.println("Процентное соотношение (запись/чтение): " + writeTimePercent + "/" + readTimePercent);
        System.out.printf("Процентное соотношение (запись/чтение): %.2f / %.2f", writeTimePercent, readTimePercent);
    }
}
