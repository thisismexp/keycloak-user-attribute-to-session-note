package at.ac.meduniwien.cedas.mim.keycloak.userattrsess;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class UserAttributeToSessionNote implements Authenticator {

    private static final Logger LOG = Logger.getLogger(UserAttributeToSessionNote.class);

    static final String PROVIDER_ID = "user-attribute-to-session-note";

    static final String CONFIG_ATTRIBUTE_NAME = "patient_id";

    public UserAttributeToSessionNote(KeycloakSession session) {
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {

        UserModel user = context.getUser();
        String value = user.getAttributeStream(CONFIG_ATTRIBUTE_NAME).findFirst().orElse(null);
        if (value != null) {
            AuthenticationSessionModel asm = context.getAuthenticationSession();
            asm.setUserSessionNote(CONFIG_ATTRIBUTE_NAME, value);
            LOG.warnf("User session note set: %s%n", CONFIG_ATTRIBUTE_NAME);
        } else {
            LOG.warnf("User attribute not found: %s%n", CONFIG_ATTRIBUTE_NAME);
        }
        context.success();
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
    }

    @Override
    public void action(AuthenticationFlowContext context) {
    }

    @Override
    public void close() {
    }
}
