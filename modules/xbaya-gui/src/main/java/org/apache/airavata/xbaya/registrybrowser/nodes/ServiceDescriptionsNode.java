/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.apache.airavata.xbaya.registrybrowser.nodes;

import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import org.apache.airavata.common.utils.SwingUtil;
import org.apache.airavata.commons.gfac.type.ServiceDescription;
import org.apache.airavata.registry.api.Registry;
import org.apache.airavata.registry.api.exception.RegistryException;
import org.apache.airavata.xbaya.appwrapper.ServiceDescriptionDialog;
import org.apache.airavata.xbaya.registrybrowser.menu.AbstractBrowserActionItem;
import org.apache.airavata.xbaya.registrybrowser.menu.AddAction;
import org.apache.airavata.xbaya.registrybrowser.menu.DeleteAction;
import org.apache.airavata.xbaya.registrybrowser.menu.RefreshAction;
import org.apache.airavata.xbaya.registrybrowser.model.ServiceDescriptions;

public class ServiceDescriptionsNode extends AbstractAiravataTreeNode {
    private ServiceDescriptions serviceDescriptions;

    public ServiceDescriptionsNode(ServiceDescriptions serviceDescriptions, TreeNode parent) {
        super(parent);
        setServiceDescriptions(serviceDescriptions);
    }

    @Override
    protected List<TreeNode> getChildren() {
        try {
            return getTreeNodeList(getServiceDescriptions().getDescriptions().toArray(), this);
        } catch (RegistryException e) {
            e.printStackTrace();
            return emptyList();
        }
    }

    @Override
    public String getCaption(boolean selected, boolean expanded, boolean leaf, boolean hasFocus) {
        return "Services";
    }

    @Override
    public Icon getIcon(boolean selected, boolean expanded, boolean leaf, boolean hasFocus) {
        return SwingUtil.createImageIcon("services.png");
    }

    public ServiceDescriptions getServiceDescriptions() {
        return serviceDescriptions;
    }

    public void setServiceDescriptions(ServiceDescriptions serviceDescriptions) {
        this.serviceDescriptions = serviceDescriptions;
    }

    @Override
    public List<String> getSupportedActions() {
        return Arrays.asList(AddAction.ID, RefreshAction.ID, DeleteAction.ID);
    }

    public boolean triggerAction(JTree tree, String action) throws Exception {
        if (action.equals(DeleteAction.ID)) {
            deleteServiceDescription(tree);
            return true;
        } else if (action.equals(AddAction.ID)) {
            ServiceDescriptionDialog serviceDescriptionDialog = new ServiceDescriptionDialog(getRegistry());
            serviceDescriptionDialog.open();
            if (serviceDescriptionDialog.isServiceCreated()) {
                refresh();
                reloadTreeNode(tree, this);
            }
            return true;
        }
        return super.triggerAction(tree, action);
    }

    private void deleteServiceDescription(JTree tree) throws Exception {
        if (askQuestion("Service descriptions",
                "Are you sure that you want to remove all service descriptions in this registry?")) {
            Registry registry = getRegistry();
            List<ServiceDescription> descriptions = getServiceDescriptions().getDescriptions();
            for (ServiceDescription descriptionWrap : descriptions) {
                registry.deleteServiceDescription(descriptionWrap.getType().getName());
            }
            refresh();
            reloadTreeNode(tree, this);
        }
    }

    @Override
    public String getActionCaption(AbstractBrowserActionItem action) {
        if (action.getID().equals(DeleteAction.ID)) {
            return "Remove all services";
        } else if (action.getID().equals(AddAction.ID)) {
            return "New service...";
        }
        return action.getDefaultCaption();
    }

    @Override
    public Icon getActionIcon(AbstractBrowserActionItem action) {
        return null;
    }

    @Override
    public String getActionDescription(AbstractBrowserActionItem action) {
        return null;
    }
}
