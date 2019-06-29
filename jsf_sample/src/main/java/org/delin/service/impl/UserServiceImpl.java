package org.delin.service.impl;

import org.delin.dao.AbstractDAO;
import org.delin.ejb.CountLoginTimeBeanRemote;
import org.delin.ejb.impl.CountLoginTimeBean;
import org.delin.entities.UserEntity;
import org.delin.service.UserService;
import org.delin.util.ParamsUtils;
import org.delin.util.ejb.EJBLookUpUtil;

import static org.delin.util.ParamsUtils.params;

public class UserServiceImpl extends AbstractDAO<UserEntity> implements UserService {
    private CountLoginTimeBeanRemote countLoginTimeBeanRemote;

    @Override
    protected String getTable() {
        return "user_basic";
    }

    @Override
    protected Class<UserEntity> getClazz() {
        return UserEntity.class;
    }

    @Override
    public boolean check(UserEntity user) {
        UserEntity realUser = get(ParamsUtils.putAll(params("name"), params(user.getName())));
        if (realUser != null && realUser.getPassword().equals(user.getPassword())) {
            if (countLoginTimeBeanRemote == null) {
                countLoginTimeBeanRemote = (CountLoginTimeBeanRemote) EJBLookUpUtil.lookup(CountLoginTimeBeanRemote.class, CountLoginTimeBean.class, "Loser", "", false);
            }
            countLoginTimeBeanRemote.inc();
            return true;
        }
        return false;
    }

    @Override
    public int getLoginCount() {
        return countLoginTimeBeanRemote.getCount();
    }

    @Override
    public boolean addUser(UserEntity user) {
        try {
            add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
