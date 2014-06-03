/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Demo_check implements EntryPoint {

	private TextField username;
	private TextField password;

	@Override
	public void onModuleLoad() {

		Panel panel = new Panel();  
		panel.setBorder(false);  
		panel.setPaddings(15);  

		final FormPanel formPanel = new FormPanel();  
		formPanel.setFrame(true);  
		formPanel.setTitle("Simple Form");  
		formPanel.setWidth(350);  
		formPanel.setLabelWidth(75);  
		

		username = new TextField("First Name", "first", 230);  
		username.setAllowBlank(false);  

		password = new TextField("Password", "password", 230); 
		password.setAllowBlank(false);
		password.setPassword(true);

		formPanel.add(username);  
		formPanel.add(password);  

		Button save = new Button("Save");  
		formPanel.addButton(save); 

		save.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {

				MessageService.Util.getInstance().authentication(username.getText(), password.getText(), new AsyncCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							System.out.println("Login Succesfully");
						} else {
							System.out.println("Authentication Failed");
						}

					}
					@Override
					public void onFailure(Throwable caught) {

						caught.printStackTrace();
					}
				});

				super.onClick(button, e);
			}	

		});

		panel.add(formPanel);  
		RootPanel.get().add(panel);  
	}

}
