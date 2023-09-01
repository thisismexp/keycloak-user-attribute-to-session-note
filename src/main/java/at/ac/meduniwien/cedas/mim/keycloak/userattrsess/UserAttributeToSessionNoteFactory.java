package at.ac.meduniwien.cedas.mim.keycloak.userattrsess;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.List;
import java.util.Collections;

public class UserAttributeToSessionNoteFactory implements AuthenticatorFactory {


    private static final List<ProviderConfigProperty> CONFIG_PROPERTIES;

    static {
        List<ProviderConfigProperty> list = ProviderConfigurationBuilder
            .create()
            .property()
            .name(UserAttributeToSessionNote.CONFIG_ATTRIBUTE_NAME)
            .label("User Attribute")
            .type(ProviderConfigProperty.STRING_TYPE)
            .helpText("Name of stored user attribute which is the name of an attribute within the UserModel.attribute map.")
            .defaultValue(UserAttributeToSessionNote.CONFIG_ATTRIBUTE_NAME)
            .add()
            .build();
        CONFIG_PROPERTIES = Collections.unmodifiableList(list);
    }

    @Override
    public String getDisplayType() {
        return "User attribute to session note";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    public static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED, AuthenticationExecutionModel.Requirement.DISABLED
    };

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getHelpText() {
        return "Use a user attribute and set it as user session note.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return CONFIG_PROPERTIES;
    }

    @Override
    public void close() {
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new UserAttributeToSessionNote(session);
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public String getId() {
        return UserAttributeToSessionNote.PROVIDER_ID;
    }
}
