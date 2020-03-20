package com.github.thiagogarbazza.examples.xmlwithsax.valuefinder;

import net.sf.saxon.Configuration;
import net.sf.saxon.lib.NamespaceConstant;
import net.sf.saxon.om.TreeInfo;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.xpath.XPathFactoryImpl;
import org.xml.sax.InputSource;

import java.io.InputStream;
import javax.xml.transform.sax.SAXSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

public class XMLValueFinderService {

  public static String valueFinder(InputStream xml, String xpath) {
    try {
      final XPathFactory xPathFactory = xPathFactory();

      final XPath xPath = xPathFactory.newXPath();
      final XPathExpression xPathExpression = xPath.compile(xpath);

      final TreeInfo treeInfo = treeInfoBuilder(xPathFactory, xml);

      return xPathExpression.evaluate(treeInfo);
    } catch (Exception e) {
      throw new RuntimeException("Error find value " + e.getMessage(), e);
    }
  }

  private static TreeInfo treeInfoBuilder(final XPathFactory xPathFactory, final InputStream xml) throws XPathException {
    final SAXSource source = new SAXSource(new InputSource(xml));

    Configuration config = ((XPathFactoryImpl) xPathFactory).getConfiguration();

    return config.buildDocumentTree(source);
  }

  private static XPathFactory xPathFactory() throws XPathFactoryConfigurationException {
    //System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON, "net.sf.saxon.xpath.XPathFactoryImpl");
    return XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON, "net.sf.saxon.xpath.XPathFactoryImpl", null);
  }
}
