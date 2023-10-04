package greenatom.projects.io;

import java.io.File;

public class FileValidation implements IOValidation {
    @Override
    public boolean exists(File file) {
        return file.exists();
    }
}
