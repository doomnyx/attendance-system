package pl.drat.dominik.service;

import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IPServiceImpl implements IPService {
    @Value("${bypass.ip.validation}")
    boolean bypassValidation;

    @Value("${school.ip.cidr}")
    String schoolNetworkCIDR;

    private boolean ipIsInRange(String ipToCheck) {
        SubnetUtils utils = new SubnetUtils(schoolNetworkCIDR);
        return utils.getInfo().isInRange(ipToCheck);
    }

    public boolean ipIsInSchoolNetworkRange(String ip) {
        if (bypassValidation) return true;
        return ipIsInRange(ip);
    }
}
