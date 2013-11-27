package org.apache.marmotta.platform.core.model.logging;

import org.apache.marmotta.platform.core.api.config.ConfigurationService;

/**
 * Add file description here!
 *
 * @author Sebastian Schaffert (sschaffert@apache.org)
 */
public abstract class LoggingOutput {
    protected String id;


    protected ConfigurationService configurationService;

    protected static String DEFAULT_PATTERN = "%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n";

    protected static String CONFIG_PATTERN  = "logging.%s.%s.%s";


    public LoggingOutput(String id, ConfigurationService configurationService) {
        this.id = id;
        this.configurationService = configurationService;
    }

    public String getName() {
        return configurationService.getStringConfiguration(getConfigKey("name"));
    }

    public void setName(String name) {
        configurationService.setConfiguration(getConfigKey("name"), name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPattern() {
        return configurationService.getStringConfiguration(getConfigKey("pattern"), DEFAULT_PATTERN);
    }

    public void setPattern(String pattern) {
        configurationService.setConfiguration(getConfigKey("pattern"), pattern);
    }


    /**
     * Internal method: return the configuration key for this logging output and the given key suffix.
     * @param key
     * @return
     */
    protected String getConfigKey(String key) {
        return String.format(CONFIG_PATTERN, getTypeIdentifier(), getId(), key);
    }

    /**
     * Get the type identifier for this kind of logging output (e.g. "file", "console", "syslog"). Used for
     * properly resolving the configuration keys.
     *
     * @return
     */
    protected abstract String getTypeIdentifier();
}
