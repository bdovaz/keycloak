/*
 * Copyright 2025 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.operator;

import java.util.Optional;

import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import org.keycloak.operator.crds.v2alpha1.deployment.Keycloak;
import org.keycloak.operator.upgrade.UpgradeType;

public final class ContextUtils {

    // context keys
    private static final String OLD_DEPLOYMENT_KEY = "current_stateful_set";
    private static final String NEW_DEPLOYMENT_KEY = "desired_new_stateful_set";
    private static final String UPGRADE_TYPE_KEY = "upgrade_type";

    private ContextUtils() {}


    public static void storeCurrentStatefulSet(Context<Keycloak> context, StatefulSet statefulSet) {
        context.managedDependentResourceContext().put(OLD_DEPLOYMENT_KEY, statefulSet);
    }

    public static StatefulSet getCurrentStatefulSet(Context<Keycloak> context) {
        return context.managedDependentResourceContext().getMandatory(OLD_DEPLOYMENT_KEY, StatefulSet.class);
    }

    public static void storeDesiredStatefulSet(Context<Keycloak> context, StatefulSet statefulSet) {
        context.managedDependentResourceContext().put(NEW_DEPLOYMENT_KEY, statefulSet);
    }

    public static StatefulSet getDesiredStatefulSet(Context<Keycloak> context) {
        return context.managedDependentResourceContext().getMandatory(NEW_DEPLOYMENT_KEY, StatefulSet.class);
    }

    public static void storeUpgradeType(Context<Keycloak> context, UpgradeType upgradeType) {
        context.managedDependentResourceContext().put(UPGRADE_TYPE_KEY, upgradeType);
    }

    public static Optional<UpgradeType> getUpgradeType(Context<Keycloak> context) {
        return context.managedDependentResourceContext().get(UPGRADE_TYPE_KEY, UpgradeType.class);
    }
}
