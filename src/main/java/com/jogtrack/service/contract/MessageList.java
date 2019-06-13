/**
 * @ MessageList.java
 *  	
 * <p>Copyright (c) 2014 Wal-Mart Stores, Inc. All rights reserved.
 * Wal-Mart PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.</p>
 */
package com.jogtrack.service.contract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The <code>MessageList</code> represents {description}
 * <p>
 * <li>{Enclosing Methods}</li> {short description}
 *
 * Created at Jun 22, 2015 2:25:00 PM
 * 
 * @author kkuma13 (last updated by $Author$)
 * @version $Revision$ $Date$
 * @since GLS-NextGen 1.0
 */
@XmlRootElement(name = "MessageList")
public class MessageList {

	private List<Message> errorMessages;

	public MessageList() {
		errorMessages = new ArrayList<Message>();
	}

	public MessageList(Message message) {
		this();
		errorMessages.add(message);
	}

	public void addMessage(Message message) {
		errorMessages.add(message);
	}

	@XmlElement
	public Collection<Message> getMessages() {
		return errorMessages;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MessageList [errorMessages=").append(errorMessages)
                .append("]");
        return builder.toString();
    }

}
