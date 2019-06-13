/**
 * @ Message.java
 *  	
 * <p>Copyright (c) 2014 Wal-Mart Stores, Inc. All rights reserved.
 * Wal-Mart PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.</p>
 */
package com.jogtrack.service.contract;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The <code>Message</code> represents {description}
 * <p>
 * Messages can be sent to clients to provide detailed feedback about the issues
 * encountered during a resource interacion. The type will typically be "error",
 * "warn", or "info". Codes may or may not be provided (if a code exists for the
 * message). Descriptions will be human-readable, and translated to the
 * requested locale (if a translation exists for the message).
 * 
 * <li>{Enclosing Methods}</li> {short description}
 *
 * Created at Jun 22, 2015 2:22:32 PM
 * 
 * @author kkuma13 (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GLS-NextGen 1.0
 */

@XmlRootElement(name = "message")
public class Message {
	@XmlAttribute(name = "type")
	private String type;
	@XmlAttribute(name = "code")
	private String code;
	@XmlAttribute(name = "desc")
	private String desc;

	public Message(String type, String code, String desc) {
		this.type = type;
		this.code = code;
		this.desc = desc;
	}

	public Message() {
	}

	public Message(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message [type=").append(type).append(", code=")
                .append(code).append(", desc=").append(desc).append("]");
        return builder.toString();
    }

	

}
