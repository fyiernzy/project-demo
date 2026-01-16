package com.demo.springbootdemo1.shared.audittrail.application;

import com.demo.springbootdemo1.shared.audittrail.domain.CustomRevisionInfo;
import com.demo.springbootdemo1.shared.utils.AuthenticationUtils;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionInfo rev = (CustomRevisionInfo) revisionEntity;
        rev.setUsername(AuthenticationUtils.getUsernameAsString());
    }
}