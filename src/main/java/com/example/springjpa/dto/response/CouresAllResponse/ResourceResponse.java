package com.example.springjpa.dto.response.CouresAllResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse{

    private String id;
    private String type;   // VIDEO / FILE / TEXT

    private FileResponse file;
    private VideoResponse video;
    private TextResponse   text;
}
