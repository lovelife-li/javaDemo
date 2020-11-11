package com.study.other.pdf.demo01;
 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
 
import java.io.IOException;
 
public class MyHeaderFooter extends PdfPageEventHelper {
    Font hfFont;
    {
        try {
            hfFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 8, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 

    // 一页加载完成触发，写入页眉和页脚
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            table.setTotalWidth(PageSize.A4.getWidth() - 100);
            table.setWidths(new int[] { 24, 24, 3});
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(-10);
            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            // 可以直接使用addCell(str)，不过不能指定字体，中文无法显示
            table.addCell(new Paragraph("北京云杉世界信息技术有限公司", hfFont));

            // 将页眉写到document中，位置可以指定，指定到下面就是页脚
            table.writeSelectedRows(0, -1, 50,PageSize.A4.getHeight() - 20, writer.getDirectContent());
        } catch (Exception de) {
            throw new ExceptionConverter(de);
        }
    }
}