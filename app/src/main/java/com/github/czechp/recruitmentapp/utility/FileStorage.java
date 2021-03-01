package com.github.czechp.recruitmentapp.utility;

import java.io.File;
import java.io.IOException;

public interface FileStorage {

    String uploadImage(String fileName, File fileInputStream) throws IOException;
}
