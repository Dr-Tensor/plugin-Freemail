/*
 * WebInterface.java
 * This file is part of Freemail
 * Copyright (C) 2011 Martin Nyhus
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package freemail.ui.web;

import java.util.HashSet;
import java.util.Set;

import freemail.FreemailPlugin;
import freemail.utils.Logger;
import freenet.clients.http.Toadlet;
import freenet.clients.http.ToadletContainer;
import freenet.pluginmanager.PluginRespirator;

public class WebInterface {
	private static final String FREEMAIL_CATEGORY_NAME = "Freemail.Menu.Name";
	private static final String CATEGORY_DEFAULT_PATH = "/Freemail/";
	private static final String CATEGORY_TITLE = "Freemail.Menu.Title";

	/**
	 * Holds all the Toadlets that must be unregistered when the web interface terminates
	 */
	private final Set<Toadlet> registeredToadlets = new HashSet<Toadlet>();
	private final ToadletContainer container;
	private final PluginRespirator pluginRespirator;

	public WebInterface(ToadletContainer container, PluginRespirator pluginRespirator, FreemailPlugin freemail) {
		this.container = container;
		this.pluginRespirator = pluginRespirator;

		//Register our menu
		pluginRespirator.getPageMaker().addNavigationCategory(CATEGORY_DEFAULT_PATH, FREEMAIL_CATEGORY_NAME, CATEGORY_TITLE, freemail);

		//Register the visible toadlets
		HomeToadlet homeToadlet = new HomeToadlet(null, pluginRespirator.getPageMaker());
		LogInToadlet loginToadlet = new LogInToadlet(null, pluginRespirator, freemail.getAccountManager());
		LogOutToadlet logoutToadlet = new LogOutToadlet(null, pluginRespirator.getSessionManager("Freemail"));
		container.register(homeToadlet, FREEMAIL_CATEGORY_NAME, homeToadlet.path(), true, "Freemail.HomeToadlet.name", "Freemail.HomeToadlet.title", false, homeToadlet);
		container.register(loginToadlet, FREEMAIL_CATEGORY_NAME, loginToadlet.path(), true, "Freemail.LoginToadlet.name", "Freemail.LoginToadlet.title", false, loginToadlet);
		container.register(logoutToadlet, FREEMAIL_CATEGORY_NAME, logoutToadlet.path(), true, "Freemail.LogoutToadlet.name", "Freemail.LogoutToadlet.title", false, logoutToadlet);
	}

	public void terminate() {
		Logger.error(this, "Unregistering toadlets");
		for(Toadlet t : registeredToadlets) {
			container.unregister(t);
		}

		Logger.error(this, "Removing navigation category");
		pluginRespirator.getPageMaker().removeNavigationCategory(FREEMAIL_CATEGORY_NAME);
	}
}
