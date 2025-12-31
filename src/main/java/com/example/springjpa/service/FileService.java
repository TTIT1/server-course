package com.example.springjpa.service;


import com.example.springjpa.dto.resquest.FileRequest;

import java.util.List;

public interface FileService {
  List<FileRequest> findAllFiles();

  FileRequest updateFileById(FileRequest fileDTO);

  FileRequest save(FileRequest fileDTO);

}
