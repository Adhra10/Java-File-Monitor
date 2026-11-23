
import java.nio.file.*;

public class FileMonitor {
    public static void main(String[] args) throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("D:/notes");
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                      StandardWatchEventKinds.ENTRY_DELETE,
                      StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Monitoring directory: " + path);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind: " + event.kind() + " | File: " + event.context());
            }
            key.reset();
        }
    }
}
