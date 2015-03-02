package eu.europa.ec.markt.dss.plugtest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import eu.europa.ec.markt.dss.signature.FileDocument;
import eu.europa.ec.markt.dss.validation102853.CommonCertificateVerifier;
import eu.europa.ec.markt.dss.validation102853.SignedDocumentValidator;
import eu.europa.ec.markt.dss.validation102853.report.Reports;

/**
 * This test is only to ensure that we dont have exception with valid? files
 */
@RunWith(Parameterized.class)
public class ETSICAdESSamplesTest {

	@Parameters(name = "Validation {index}: {0}")
	public static Collection<Object[]> data() {
		File folder = new File("src/test/resources/plugtest/cades");
		Collection<File> listFiles = FileUtils.listFiles(folder, new String[] { "p7m" }, true);
		Collection<Object[]> dataToRun = new ArrayList<Object[]>();
		for (File file : listFiles) {
			dataToRun.add(new Object[] { file });
		}
		return dataToRun;
	}

	private File fileToTest;

	public ETSICAdESSamplesTest(File fileToTest) {
		this.fileToTest = fileToTest;
	}

	@Test
	public void testValidate() {
		SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(new FileDocument(fileToTest));
		validator.setCertificateVerifier(new CommonCertificateVerifier());
		Reports validateDocument = validator.validateDocument();
		assertNotNull(validateDocument);
	}

}
