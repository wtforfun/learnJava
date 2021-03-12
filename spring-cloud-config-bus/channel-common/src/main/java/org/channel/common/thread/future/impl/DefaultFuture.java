package org.channel.common.thread.future.impl;

import java.util.concurrent.FutureTask;

import org.channel.common.thread.callable.EtocCallable;
import org.channel.common.thread.future.EtocFuture;

/**
 * 
 * 特性线程默认现实
 * 
 * @author  Administrator
 * @version  [版本号, 2015-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultFuture<REQUEST_MSG> extends FutureTask<REQUEST_MSG> implements EtocFuture<REQUEST_MSG> {

    private EtocCallable<REQUEST_MSG> callable = null;
	
	public DefaultFuture(EtocCallable<REQUEST_MSG> callable) {
		super(callable);
		this.callable = callable;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onException() {
		callable.onException();
	}
}
