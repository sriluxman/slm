package org.eclipse.slm.resource_management.service.rest.handler;

import org.eclipse.slm.common.model.DeploymentType;
import org.eclipse.slm.resource_management.model.capabilities.CapabilityType;
import org.eclipse.slm.resource_management.model.capabilities.DeploymentCapability;
import org.eclipse.slm.resource_management.model.capabilities.actions.AwxCapabilityAction;
import org.eclipse.slm.resource_management.model.capabilities.actions.CapabilityAction;
import org.eclipse.slm.resource_management.model.capabilities.actions.CapabilityActionType;
import org.eclipse.slm.resource_management.model.cluster.ClusterCreateRequest;
import org.eclipse.slm.resource_management.model.cluster.ClusterMemberType;
import org.eclipse.slm.resource_management.model.consul.capability.MultiHostCapabilityService;
import org.eclipse.slm.resource_management.model.resource.BasicResource;

import java.util.*;

public class ClusterHandlerITConfigDockerSwarmCapability {
    static UUID capabilityId = UUID.fromString("11498038-f0b0-421c-8e33-241b745b1132");
    static DeploymentCapability dockerSwarmCapability = new DeploymentCapability(capabilityId);
    private static String name = "Docker-Swarm";
    private static String logo = "mdi-docker";
    private static List<CapabilityType> types = Arrays.asList(
            CapabilityType.SETUP,
            CapabilityType.DEPLOY,
            CapabilityType.SCALE
    );
    private static AwxCapabilityAction awxCapabilityAction = new AwxCapabilityAction(
            "repo",
            "branch",
            "playbook"
    );
    private static Map<CapabilityActionType, CapabilityAction> actions = Map.of(
            CapabilityActionType.INSTALL, awxCapabilityAction,
            CapabilityActionType.UNINSTALL, awxCapabilityAction,
            CapabilityActionType.DEPLOY, awxCapabilityAction,
            CapabilityActionType.UNDEPLOY, awxCapabilityAction,
            CapabilityActionType.SCALE_UP, awxCapabilityAction,
            CapabilityActionType.SCALE_DOWN, awxCapabilityAction
    );
    private static List<DeploymentType> deploymentTypes = Arrays.asList(
            DeploymentType.DOCKER_CONTAINER,
            DeploymentType.DOCKER_COMPOSE
    );
    private static List<ClusterMemberType> clusterMemberTypes = Arrays.asList(
            new ClusterMemberType("docker_manager", "Manager", 3, false),
            new ClusterMemberType("docker_worker", "Worker", 1, true)
    );

    public static DeploymentCapability get() {
        dockerSwarmCapability.setName(name);
        dockerSwarmCapability.setLogo(logo);
        dockerSwarmCapability.setType(types);
        dockerSwarmCapability.setActions(actions);
        dockerSwarmCapability.setSupportedDeploymentTypes(deploymentTypes);
        dockerSwarmCapability.setClusterMemberTypes(clusterMemberTypes);

        return dockerSwarmCapability;
    }

    public static BasicResource[] clusterMembers = new BasicResource[] {
            new BasicResource(UUID.randomUUID(), "docker-swarm-host-1", "1.1.1.1"),
            new BasicResource(UUID.randomUUID(), "docker-swarm-host-2", "1.1.1.2"),
            new BasicResource(UUID.randomUUID(), "docker-swarm-host-3", "1.1.1.3"),
            new BasicResource(UUID.randomUUID(), "docker-swarm-host-4", "1.1.1.4"),
    };

    private static Map<UUID, String> clusterMemberMapping = Map.of(
            clusterMembers[0].getId(), clusterMemberTypes.get(0).getName(),
            clusterMembers[1].getId(), clusterMemberTypes.get(0).getName(),
            clusterMembers[2].getId(), clusterMemberTypes.get(0).getName(),
            clusterMembers[3].getId(), clusterMemberTypes.get(1).getName()
    );

    public static MultiHostCapabilityService getCapabilityService() {
        return new MultiHostCapabilityService(
                ClusterHandlerITConfigDockerSwarmCapability.get(),
                clusterMemberMapping,
                ClusterHandlerITConfigDockerSwarmCapability.getCapabilityService().getId()
        );
    }

    public static ClusterCreateRequest getClusterCreateRequest() {
        ClusterCreateRequest clusterCreateRequest = new ClusterCreateRequest(capabilityId);
        clusterCreateRequest.setClusterMembers(clusterMemberMapping);
        clusterCreateRequest.setSkipInstall(false);
        clusterCreateRequest.setConfigParameterValues(new HashMap<>());

        return clusterCreateRequest;
    }
}
