package org.openwebflow.mgr.hibernate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openwebflow.OwfException;
import org.openwebflow.identity.IdentityMembershipManager;
import org.openwebflow.mgr.ext.IdentityMembershipManagerEx;
import org.openwebflow.mgr.hibernate.dao.SqlMembershipDao;
import org.openwebflow.mgr.hibernate.entity.SqlMembershipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SqlMembershipManager implements IdentityMembershipManager, IdentityMembershipManagerEx
{
	@Autowired
	SqlMembershipDao _dao;

	@Override
	public List<String> findGroupIdsByUser(String userId)
	{
		Map<String, Object> names = new HashMap<String, Object>();
		try
		{
			for (Object ms : _dao.findByUser(userId))
			{
				names.put(((SqlMembershipEntity) ms).getGroupId(), 0);
			}
		}
		catch (Exception e)
		{
			throw new OwfException(e);
		}

		return new ArrayList<String>(names.keySet());
	}

	@Override
	public List<String> findUserIdsByGroup(String groupId)
	{
		Map<String, Object> names = new HashMap<String, Object>();
		try
		{
			for (Object ms : _dao.findByGroup(groupId))
			{
				names.put(((SqlMembershipEntity) ms).getUserId(), 0);
			}
		}
		catch (Exception e)
		{
			throw new OwfException(e);
		}

		return new ArrayList<String>(names.keySet());
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeAll() throws Exception
	{
		_dao.deleteAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMembership(String userId, String groupId) throws Exception
	{
		_dao.saveMembership(userId, groupId);
	}
}