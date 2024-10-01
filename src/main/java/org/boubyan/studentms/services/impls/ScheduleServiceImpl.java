package org.boubyan.studentms.services.impls;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.boubyan.studentms.services.ScheduleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@Transactional
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

	@Value("${report.template.courseScheduleReport.path}")
	private String courseScheduleReportPath;

	@Value("${reportsBasePath}")
	private String reportsBasePath;

	@Override
	public void printCourseSchedule(Integer courseId, HttpServletResponse response) {
		File exportedFile = null;
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(courseScheduleReportPath);
			Map<String, Object> params = new HashMap<>();
			params.put("PARAM_NAME", null);

			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			long longDateTime = new Date().getTime();
			String EXPORTED_FILE_PATH = reportsBasePath + longDateTime + ".pdf";
			exportedFile = new File(EXPORTED_FILE_PATH);
			JasperExportManager.exportReportToPdfFile(print, EXPORTED_FILE_PATH);

			String mimeType = URLConnection.guessContentTypeFromName(exportedFile.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);
			response.setHeader("Content-Disposition",
					String.format("inline; filename=\"" + exportedFile.getName() + "\""));
			response.setContentLength((int) exportedFile.length());

			try (InputStream inputStream = new BufferedInputStream(new FileInputStream(exportedFile));) {
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e);
		} finally {
			if (exportedFile != null && exportedFile.exists()) {
				exportedFile.delete();
			}
		}
	}
}
