package com.example.vaccnow.util;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "pdf")
public class PdfGeneratorProperties {

    private String pdfDir;

    private String reportFileName;

    private String reportFileNameDateFormat;

    private String localDateFormat;

    private String logoImgPath;

    private Float[] logoImgScale;

    private String currencySymbol;

    private int noOfColumns;

    private List<String> columnNames;

}