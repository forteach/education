package com.forteach.education.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hslf.usermodel.HSLFAutoShape;
import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * 通过poi将doc、docx、ppt、pptx转为html
 * 保存图片的位置在文件所在目录，以文件名创建文件夹下
 */
@Slf4j
@Component
public class PoiUtils {
    @Autowired
    private Environment environment;

    private static String rootPath;
    private static String IMAGE_SERVER;
//    public void speak(){
//        environment.getProperty("file.server");
//        environment.getProperty("upload.rootPath");
//    }
//
//    public static void main(String[] args) {
//        System.out.println("rootPath ==> " + rootPath);
//        System.out.println("images.server ==> " + IMAGE_SERVER);
//    }

    /**
     * poi office文件转html，支持doc,docx,ppt,pptx
     * 根据源文件在同一目录下生成相同名称的html文件
     */
    public static boolean officeToHtml(String filePath) {
        filePath = rootPath + filePath;
        String htmlFilePath = FileUtils.getFileNameWithoutExtension(filePath) + ".html";
        try {
            if (checkFile(filePath, "doc")) {
                return wordToHtml03(filePath, htmlFilePath);
            } else if (checkFile(filePath, "docx")) {
                return wordToHtml07(filePath, htmlFilePath);
            } else if (checkFile(filePath, "ppt")) {
                return pptToHtml03(filePath, htmlFilePath);
            } else if (checkFile(filePath, "pptx")) {
                return pptToHtml07(filePath, htmlFilePath);
            } else {
                log.error("poi OfficeToHtml出错，不支持的文件格式");
                return false;
            }
        } catch (Exception e) {
            log.error("poi OfficeToHtml出错：", e);
            return false;
        }
    }

    /**
     * Word03 转为 HTML
     *
     * @param fileName
     * @param outputFile
     */
    public static boolean wordToHtml03(String fileName, String outputFile) {
        if (!(checkFile(fileName, "doc") || checkFile(fileName, "docx"))) {
            log.error("word03文件转html出错，不支持类型为：" + fileName.substring(fileName.lastIndexOf(".")) + " 的文件");
            return false;
        }
        HWPFDocument wordDoc = null;
        WordToHtmlConverter wthc = null;
        try {
            wordDoc = new HWPFDocument(new FileInputStream(fileName));
            wthc = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        } catch (Exception e) {
            log.error("word03转html失败", e);
            return false;
        }
        //html引用图片位置
        wthc.setPicturesManager((bytes, pt, string, f, f1) -> getImageUrl(fileName) + string);
        wthc.processDocument(wordDoc);
        List<Picture> pics = wordDoc.getPicturesTable().getAllPictures();
        fileExists(getImageSavePath(fileName));
        if (null != pics && pics.size() > 0) {
            for (Picture pic : pics) {
                try {
                    //生成图片位置
                    pic.writeImageContent(new FileOutputStream(getImageSavePath(fileName) + pic.suggestFullFileName()));
                } catch (IOException e) {
                    log.error("word03转html失败", e);
                    return false;
                }
            }
        }
        Document htmlDocument = wthc.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            log.error("word03转html失败", e);
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("word03转html文件流关闭失败", e);
            }
        }
        String htmlStr = new String(out.toByteArray());
        return writeFile(htmlStr, outputFile);
    }

    /**
     * Word07 转为 HTML
     *
     * @param fileName
     * @param outputFile
     */
    public static boolean wordToHtml07(String fileName, String outputFile) {
        if (!checkFile(fileName, "docx")) {
            log.error("word07文件转html出错，不支持类型为：" + fileName.substring(fileName.lastIndexOf(".")) + " 的文件");
            return false;
        }
        //读取文档内容
        XWPFDocument document = null;
        try {
            InputStream in = new FileInputStream(fileName);
            document = new XWPFDocument(in);
        } catch (IOException e) {
            log.error("word07转html失败", e);
            return false;
        }
        //加载html页面时图片路径
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new BasicURIResolver(getImageUrl(fileName)));
        //图片保存文件夹路径
        fileExists(getImageSavePath(fileName));
        options.setExtractor(new FileImageExtractor(new File(getImageSavePath(fileName))));
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(outputFile));
            XHTMLConverter.getInstance().convert(document, out, options);
            return true;
        } catch (IOException e) {
            log.error("word07转html失败", e);
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("word07转html文件流关闭失败", e);
            }
        }
    }

    /**
     * excel to html
     *
     * @param path
     * @param file
     */
    // todo 待完成
    public static void testExcel(String path, String file) {
        HSSFWorkbook excelBook = null;
        ExcelToHtmlConverter excelToHtmlConverter = null;
        try {
            InputStream input = new FileInputStream(path + file);
            excelBook = new HSSFWorkbook(input);
            excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        //加载html页面时图片路径
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new BasicURIResolver(getImageUrl(path)));
        //图片保存文件夹路径
        options.setExtractor(new FileImageExtractor(new File(getImageSavePath(path))));
        excelToHtmlConverter.setOutputRowNumbers(false);
        excelToHtmlConverter.setOutputHiddenRows(false);
        excelToHtmlConverter.setOutputColumnHeaders(false);
        excelToHtmlConverter.setOutputHiddenColumns(true);
        excelToHtmlConverter.processWorkbook(excelBook);
        List pics = excelBook.getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                HSSFPictureData pic = (HSSFPictureData) pics.get(i);
                try {
//                        pic.writeImageContent (new FileOutputStream (path + pic.suggestFullFileName() ) );
                    new FileOutputStream(path + "11").write(pic.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        Document htmlDocument = excelToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String content = new String(outStream.toByteArray());

        try {
            FileUtils.writeStringToFile(new File(path, "exportExcel.html"), content, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ppt03转html
     * filepath:源文件
     * htmlname:生成html名称
     */
    public static boolean pptToHtml03(String filepath, String outputFile) {
        File file = new File(filepath);
        // 读入PPT文件
        if (!checkFile(filepath, "ppt")) {
            log.error("ppt03文件转html出错，不支持类型为：" + FileUtils.getFileExtension(filepath) + " 的文件");
            return false;
        }
        FileInputStream is = null;
        SlideShow ppt;
        try {
            is = new FileInputStream(file);
            ppt = SlideShowFactory.create(is);
        } catch (IOException e) {
            log.error("ppt03文件转html出错：", e);
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("ppt03文件转html关闭文件流失败：", e);
            }
        }

        Dimension pgsize = ppt.getPageSize();
        List<Slide> slide = ppt.getSlides();
        FileOutputStream out = null;
        String imghtml = "";
        //保存图片位置
        fileExists(getImageSavePath(filepath));
        for (int i = 0; i < slide.size(); i++) {
            for (Object o : slide.get(i).getShapes()) {
                if (o instanceof HSLFAutoShape) {
                    HSLFAutoShape shapes = (HSLFAutoShape) o;
                    List<HSLFTextParagraph> list = shapes.getTextParagraphs();
                    for (HSLFTextParagraph hslfTextRuns : list) {
                        for (HSLFTextRun hslfTextRun : hslfTextRuns.getTextRuns()) {
                            hslfTextRun.setFontFamily("宋体");
                        }
                    }
                } else if (o instanceof HSLFTable) {
                    HSLFTable hslfTable = (HSLFTable) o;
                    int rowSize = hslfTable.getNumberOfRows();
                    int columnSize = hslfTable.getNumberOfColumns();
                    for (int j = 0; j < rowSize; j++) {
                        for (int k = 0; k < columnSize; k++) {
                            for (int l = 0; l < hslfTable.getCell(j, k).getTextParagraphs().size(); l++) {
                                HSLFTextParagraph hslfTextRuns = hslfTable.getCell(j, k).getTextParagraphs().get(l);
                                for (int m = 0; m < hslfTextRuns.getTextRuns().size(); m++) {
                                    HSLFTextRun textRun = hslfTextRuns.getTextRuns().get(m);
                                    //todo 设置字体失败，输出html依旧会乱码
                                    textRun.setFontFamily("宋体");
                                }
                            }
                        }
                    }
                }
            }
            BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.BLUE);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
            slide.get(i).draw(graphics);
            // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径与源文件同一个目录
            try {
                out = new FileOutputStream(getImageSavePath(filepath) + (i + 1) + ".jpeg");
                ImageIO.write(img, "jpeg", out);
            } catch (IOException e) {
                log.error("ppt03文件转html出错：", e);
                try {
                    out.close();
                } catch (IOException e1) {
                    log.error("ppt03文件转html关闭文件流失败：", e);
                }
                return false;
            }
            //图片在html加载路径
            String imgs = getImageUrl(filepath) + (i + 1) + ".jpeg";
            imghtml += "<img src=\'" + imgs + "\' style=\'width:1200px;height:830px;vertical-align:text-bottom;\'><br><br><br><br>";

        }
        DOMSource domSource = new DOMSource();
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            log.error("ppt03文件转html出错：", e);
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("ppt03文件转html关闭文件流失败：", e);
            }
        }

        String ppthtml = "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>" + imghtml + "</body></html>";
        try {
            FileUtils.writeStringToFile(new File(outputFile), ppthtml, "utf-8");
        } catch (IOException e) {
            log.error("ppt03文件转html出错：", e);
            return false;
        }
        return true;
    }

    /**
     * ppt07转html
     * filepath:源文件
     * outputFile:生成html名称
     */
    public static boolean pptToHtml07(String filepath, String outputFile) {
        File file = new File(filepath);
        // 读入PPT文件
        if (!checkFile(filepath, "pptx")) {
            log.error("ppt07文件转html出错，不支持类型为：" + FileUtils.getFileExtension(filepath) + " 的文件");
            return false;
        }
        FileInputStream is = null;
        SlideShow ppt;
        try {
            is = new FileInputStream(file);
            ppt = SlideShowFactory.create(is);
        } catch (IOException e) {
            log.error("ppt07文件转html出错：", e);
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("ppt07文件转html关闭文件流失败：", e);
            }
        }
        Dimension pgsize = ppt.getPageSize();
        List<XSLFSlide> pptPageXSLFSLiseList = ppt.getSlides();
        FileOutputStream out = null;
        String imghtml = "";
        //保存图片位置
        fileExists(getImageSavePath(filepath));
        for (int i = 0; i < pptPageXSLFSLiseList.size(); i++) {
            for (XSLFShape shape : pptPageXSLFSLiseList.get(i).getShapes()) {
                //设置文字字体
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape tsh = (XSLFTextShape) shape;
                    for (XSLFTextParagraph p : tsh) {
                        for (XSLFTextRun r : p) {
                            r.setFontFamily("宋体");
                        }
                    }
                    //设置表格字体
                } else if (shape instanceof XSLFTable) {
                    XSLFTable table = (XSLFTable) shape;
                    int rowSize = table.getNumberOfRows();
                    int columnSize = table.getNumberOfColumns();
                    for (int j = 0; j < rowSize; j++) {
                        for (int k = 0; k < columnSize; k++) {
                            for (int l = 0; l < table.getCell(j, k).getTextParagraphs().size(); l++) {
                                XSLFTextParagraph xslfTextRuns = table.getCell(j, k).getTextParagraphs().get(l);
                                for (int m = 0; m < xslfTextRuns.getTextRuns().size(); m++) {
                                    xslfTextRuns.getTextRuns().get(m).setFontFamily("宋体");
                                }
                            }
                        }
                    }
                }
            }
            BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
            pptPageXSLFSLiseList.get(i).draw(graphics);
            //设置图片存放位置
            String Imgname = getImageSavePath(filepath) + (i + 1) + ".jpg";
            try {
                out = new FileOutputStream(Imgname);
                ImageIO.write(img, "jpg", out);
            } catch (IOException e) {
                log.error("ppt07文件转html出错：", e);
                try {
                    out.close();
                } catch (IOException e1) {
                    log.error("ppt07文件转html关闭流出错：", e);
                }
                return false;
            }
            //图片在html加载路径
            String imgs = getImageUrl(filepath) + (i + 1) + ".jpg";
            imghtml += "<img src=\'" + imgs + "\' style=\'width:1200px;height:830px;vertical-align:text-bottom;\'><br><br><br><br>";
        }

        DOMSource domSource = new DOMSource();
        StreamResult streamResult = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            log.error("ppt07文件转html出错：", e);
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("ppt07文件转html关闭流出错：", e);
                return false;
            }
        }

        String ppthtml = "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>" + imghtml + "</body></html>";
        try {
            FileUtils.writeStringToFile(new File(outputFile), ppthtml, "utf-8");
            return true;
        } catch (Exception e) {
            log.error("ppt07文件转html关闭流出错：", e);
            return false;
        }
    }

    /**
     * 输出文件流
     *
     * @param content
     * @param path
     * @return
     */
    public static boolean writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;

        File file = new File(path);

        try {
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(content);
            return true;
        } catch (IOException e) {
            log.error("输出文件出错", e);
            return false;
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error("输出文件关闭流出错", e);
            }
        }
    }

    /**
     * 判断文件夹是否存在，不存在则新建
     *
     * @param path
     */
    private static void fileExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 检查文件类型
     *
     * @param fileName
     * @return
     */
    public static boolean checkFile(String fileName, String type) {
        boolean flag = false;
        String suffixname = FileUtils.getFileExtension(fileName);
        if (suffixname != null && suffixname.equalsIgnoreCase(type)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据文件全路径获取文件所在路径
     *
     * @param fileFullName
     * @return
     */
    public static String getFilePath(String fileFullName) {
        File file = new File(fileFullName);
        String filePath = fileFullName.replace(file.getName(), "");
        return filePath;
    }

    private static String getImageUrl(String filePath) {
        filePath = filePath.replace(rootPath, "");
        //图片引用地址需要去掉 rootpath
        return IMAGE_SERVER + FileUtils.getFileNameWithoutExtension(filePath) + "/";
    }

    private static String getImageSavePath(String filePath) {
        return FileUtils.getFileNameWithoutExtension(filePath) + File.separator;
    }

}
