package com.github.czechp.recruitmentapp.utility;

import java.io.File;
import java.util.HashMap;

public interface FileStorage {

    String updateFile(String fileName, HashMap<String, String> metadata, byte[] fileBytes);
}
