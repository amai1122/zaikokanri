/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.taglibs.standard.tag.rt.xml;

import javax.servlet.jsp.JspTagException;
import javax.xml.transform.Result;

import org.apache.taglibs.standard.tag.common.xml.TransformSupport;

/**
 * <p>A handler for &lt;transform&gt; that supports rtexprvalue-based
 * attributes.</p>
 *
 * @author Shawn Bayern
 */

public class TransformTag extends TransformSupport {

    //*********************************************************************
    // Accessor methods

    // Deprecated as of JSTL 1.1
    // for tag attribute

    public void setXml(Object xml) throws JspTagException {
        setDoc(xml);
    }

    // 'doc' replaces 'xml' as of JSTL 1.1

    public void setDoc(Object xml) throws JspTagException {
        this.xml = xml;
        this.xmlSpecified = true;
    }

    // Deprecated as of JSTL 1.1
    // for tag attribute

    public void setXmlSystemId(String xmlSystemId) throws JspTagException {
        this.xmlSystemId = xmlSystemId;
    }

    // 'docSystemId' replaces 'xmlSystemId' as of JSTL 1.1

    public void setDocSystemId(String xmlSystemId) throws JspTagException {
        this.xmlSystemId = xmlSystemId;
    }

    // for tag attribute

    public void setXslt(Object xslt) throws JspTagException {
        this.xslt = xslt;
    }

    // for tag attribute

    public void setXsltSystemId(String xsltSystemId) throws JspTagException {
        this.xsltSystemId = xsltSystemId;
    }

    // for tag attribute

    public void setResult(Result result) throws JspTagException {
        this.result = result;
    }

}
