package com.ozansaribal.n11bootcamp.service.entityService;

import com.ozansaribal.n11bootcamp.dao.MemberDao;
import com.ozansaribal.n11bootcamp.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberEntityService extends BaseEntityService<Member, MemberDao> {

    public MemberEntityService(MemberDao dao) {
        super(dao);
    }

    public Member findByUsername(String username){
        return getDao().findByUsername(username);
    }

}
