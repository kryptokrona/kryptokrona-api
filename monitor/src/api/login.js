import Keycloak from "keycloak-js";
import {setUser} from "../stores/user";

// temporary
const PUBLIC_KEYCLOAK_URL="http://localhost:18080/"
const PUBLIC_KEYCLOAK_REALM="KryptokronaAPIKeycloak"
const PUBLIC_KEYCLOAK_CLIENT="login-app"

let keycloak;

export function login () {
     keycloak.init({onLoad: 'login-required'}).then(function(authenticated) {
        console.log(authenticated ? 'authenticated' : 'not authenticated');
        setUser(keycloak.idTokenParsed.preferred_username, keycloak.idTokenParsed.email);
    }).catch(function() {
        console.log('failed to initialize');
    });
}

export function initKeycloak () {
    keycloak = new Keycloak({
        url: PUBLIC_KEYCLOAK_URL,
        realm: PUBLIC_KEYCLOAK_REALM,
        clientId: PUBLIC_KEYCLOAK_CLIENT,
        "enable-cors": true,
        "public-client": true,
        "ssl-required": "external",
        "confidential-port": 0
    }); 
}


/*
browser won’t do a full redirect to the Keycloak server and back to your application
    keycloak.init({
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
    }) */

//TODO: Update token before sending request
/*
    keycloak.updateToken(30).then(function() {
        loadData();
    }).catch(function() {
        alert('Failed to refresh token');
});
*/




