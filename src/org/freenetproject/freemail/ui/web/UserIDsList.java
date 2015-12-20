package org.freenetproject.freemail.ui.web;

import freenet.client.HighLevelSimpleClient;
import freenet.clients.http.*;
import freenet.pluginmanager.PluginNotFoundException;
import freenet.support.api.HTTPRequest;
import org.freenetproject.freemail.wot.Identity;
import org.freenetproject.freemail.wot.WoTConnection;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

/**
 * Created by Naruto on 19/12/15.
 */
public class UserIDsList extends Toadlet {
    private static final String PATH = WebInterface.PATH + "/UserIDsList";
    private static WoTConnection wotConnection;
    private static ToadletContext toadletContext;

    protected UserIDsList(HighLevelSimpleClient client) {
        super(client);
    }


    WebPage.HTTPResponse makeWebPageGet(URI uri, HTTPRequest req, ToadletContext ctx, PageNode page) throws IOException {
        return null;
    }

    WebPage.HTTPResponse makeWebPagePost(URI uri, HTTPRequest req, ToadletContext ctx, PageNode page) throws IOException {
        return null;
    }

    boolean requiresValidSession() {
        return true;
    }

    @Override
    public void handleMethodGET(URI uri, HTTPRequest request, ToadletContext ctx) throws ToadletContextClosedException, IOException, RedirectException {

    }

    @Override
    public String path() {
        return PATH;
    }

    String[] findUsers(ToadletContext ctx, LoginManager loginManager) {
        String identity = loginManager.getSession(ctx).getUserID();
        try {
            Set<Identity> users;
            users = wotConnection.getAllTrustedIdentities(identity);
            String[] possiblePeople = new String[users.size()];
            int i = 0;
            for(Identity user: users) {
                possiblePeople[i] = user.toString();
                i++;
            }
            return possiblePeople;
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}