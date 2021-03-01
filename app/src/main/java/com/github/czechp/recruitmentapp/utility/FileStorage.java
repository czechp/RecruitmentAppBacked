package com.github.czechp.recruitmentapp.utility;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileStorage {
    Optional<String> uploadFile(long questionId, String fileName, File file) throws IOException;

}
