package org.channel.config.service.links;

import org.channel.config.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbsLinksService
{
	@Autowired
	protected ContextHolder context;
}
