package eu.europa.esig.dss.validation.process.bbb.xcv.sub;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import eu.europa.esig.dss.jaxb.detailedreport.XmlConstraint;
import eu.europa.esig.dss.jaxb.detailedreport.XmlStatus;
import eu.europa.esig.dss.jaxb.detailedreport.XmlSubXCV;
import eu.europa.esig.dss.jaxb.diagnostic.XmlCertificate;
import eu.europa.esig.dss.validation.process.bbb.xcv.sub.checks.CertificateSupportedBySSCDCheck;
import eu.europa.esig.dss.validation.reports.wrapper.CertificateWrapper;
import eu.europa.esig.jaxb.policy.Level;
import eu.europa.esig.jaxb.policy.LevelConstraint;

public class CertificateSupportedBySSCDCheckTest {

	@Test
	public void certificateSupportedBySSCDCheck() throws Exception {
		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlCertificate xc = new XmlCertificate();
		xc.getCertificatePolicyIds().add("0.4.0.1456.1.1");

		XmlSubXCV result = new XmlSubXCV();
		CertificateSupportedBySSCDCheck csbsc = new CertificateSupportedBySSCDCheck(result, new CertificateWrapper(xc),
				constraint);
		csbsc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.OK, constraints.get(0).getStatus());
	}

	@Test
	public void failedCertificateSupportedBySSCDCheck() throws Exception {
		LevelConstraint constraint = new LevelConstraint();
		constraint.setLevel(Level.FAIL);

		XmlCertificate xc = new XmlCertificate();
		xc.getCertificatePolicyIds().add("0.4.0.1456.1.12");

		XmlSubXCV result = new XmlSubXCV();
		CertificateSupportedBySSCDCheck csbsc = new CertificateSupportedBySSCDCheck(result, new CertificateWrapper(xc),
				constraint);
		csbsc.execute();

		List<XmlConstraint> constraints = result.getConstraint();
		assertEquals(1, constraints.size());
		assertEquals(XmlStatus.NOT_OK, constraints.get(0).getStatus());
	}
}
