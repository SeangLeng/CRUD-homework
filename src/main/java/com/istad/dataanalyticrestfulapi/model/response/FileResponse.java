package com.istad.dataanalyticrestfulapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileResponse {
    private String filename;
    private String fileDownloadUri;
    private String filetype;
    private long size;
}
